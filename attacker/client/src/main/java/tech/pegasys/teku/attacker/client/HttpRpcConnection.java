package tech.pegasys.teku.attacker.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicLong;
import tech.pegasys.teku.infrastructure.async.SafeFuture;

public class HttpRpcConnection implements RpcConnection {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final HttpClient httpClient;
    private final URI endpoint;
    private final AtomicLong idCounter = new AtomicLong(0);

    public HttpRpcConnection(URI endpoint) {
        this.endpoint = endpoint;
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public <T> SafeFuture<T> call(String method, Class<T> responseType, Object... params) {
        SafeFuture<T> future = new SafeFuture<>();

        try {
            ObjectNode requestNode = MAPPER.createObjectNode();
            requestNode.put("jsonrpc", "2.0");
            requestNode.put("id", idCounter.incrementAndGet());
            requestNode.put("method", method);

            ArrayNode paramsNode = MAPPER.createArrayNode();
            for (Object param : params) {
                paramsNode.addPOJO(param);
            }
            requestNode.set("params", paramsNode);

            String requestBody = MAPPER.writeValueAsString(requestNode);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(endpoint)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        try {
                            ObjectNode responseNode = (ObjectNode) MAPPER.readTree(response.body());
                            if (responseNode.has("error")) {
                                throw new RuntimeException("RPC error: " + responseNode.get("error").toString());
                            }
                            return MAPPER.treeToValue(responseNode.get("result"), responseType);
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to parse RPC response", e);
                        }
                    })
                    .thenAccept(future::complete)
                    .exceptionally(e -> {
                        future.completeExceptionally(e);
                        return null;
                    });
        } catch (Exception e) {
            future.completeExceptionally(e);
        }

        return future;
    }

    @Override
    public void close() {
        // HttpClient doesn't need explicit closing
    }
}
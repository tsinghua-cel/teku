package tech.pegasys.teku.attacker.client;

import java.net.URI;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.apache.tuweni.bytes.Bytes;
import tech.pegasys.teku.infrastructure.async.SafeFuture;

/**
 * AttackerClient defines typed wrappers for the Attacker RPC API.
 */
public class AttackerClient {
    private final RpcConnection rpcConnection;
    private final int validatorIndex;

    /**
     * Dial connects a client to the given URL.
     *
     * @param rawUrl The URL to connect to
     * @param validatorIndex The validator index
     * @return A new AttackerClient
     * @throws Exception If connection fails
     */
    public static AttackerClient dial(String rawUrl, int validatorIndex) throws Exception {
        return new AttackerClient(new HttpRpcConnection(URI.create(rawUrl)), validatorIndex);
    }

    /**
     * Creates a client that uses the given RPC connection.
     *
     * @param rpcConnection The RPC connection
     * @param validatorIndex The validator index
     */
    public AttackerClient(RpcConnection rpcConnection, int validatorIndex) {
        this.rpcConnection = rpcConnection;
        this.validatorIndex = validatorIndex;
    }

    /**
     * Close closes the underlying RPC connection.
     */
    public void close() {
        rpcConnection.close();
    }
}
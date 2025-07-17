/*
 * Copyright Consensys Software Inc., 2025
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package tech.pegasys.teku.attacker.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import tech.pegasys.teku.infrastructure.async.SafeFuture;

public class HttpRpcConnection implements RpcConnection {
  private final JsonRpcHttpClient rpcClient;

  public HttpRpcConnection(URL endpoint) {
    this.rpcClient = new JsonRpcHttpClient(new ObjectMapper(), endpoint);
  }

  @Override
  public <T> SafeFuture<T> call(String method, Class<T> responseType, Object... params) {
    SafeFuture<T> future = new SafeFuture<>();
    CompletableFuture.runAsync(
        () -> {
          try {
            T response = rpcClient.invoke(method, params, responseType);
            future.complete(response);
          } catch (Exception e) {
            future.completeExceptionally(e);
          }
        });
    return future;
  }

  @Override
  public void close() {
    // No explicit close needed for JsonRpcHttpClient
  }
}

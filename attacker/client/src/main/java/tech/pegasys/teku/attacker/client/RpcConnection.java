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

import java.net.URI;

/** AttackerClient defines typed wrappers for the Attacker RPC API. */
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

  /** Close closes the underlying RPC connection. */
  public void close() {
    rpcConnection.close();
  }
}

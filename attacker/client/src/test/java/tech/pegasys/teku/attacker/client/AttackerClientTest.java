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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.pegasys.teku.infrastructure.async.SafeFuture;

public class AttackerClientTest {

  private JsonRpcHttpClient rpcClient;
  private AttackerClient attackerClient;

  @BeforeEach
  public void setup() throws Exception {
    rpcClient = mock(JsonRpcHttpClient.class);
    RpcConnection rpcConnection = new HttpRpcConnection(rpcClient);
    attackerClient = new AttackerClient(rpcConnection, 0);
  }

  @Test
  public void shouldCallBlockGetNewParentRoot() throws Exception {
    AttackerResponse expectedResponse =
        new AttackerResponse(AttackerCommand.CMD_CONTINUE, "success");
    when(rpcClient.invoke(eq("block_getNewParentRoot"), any(), eq(AttackerResponse.class)))
        .thenReturn(expectedResponse);

    SafeFuture<AttackerResponse> future =
        attackerClient.blockGetNewParentRoot(1234L, "pubkey", "parentRoot");
    AttackerResponse response = future.get();

    assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
    assertThat(response.getResult()).isEqualTo("success");
  }
}

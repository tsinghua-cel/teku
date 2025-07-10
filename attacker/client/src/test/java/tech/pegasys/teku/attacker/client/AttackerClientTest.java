package tech.pegasys.teku.attacker.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import tech.pegasys.teku.infrastructure.async.SafeFuture;

public class AttackerClientTest {

    private static final long SLOT = 1234L;
    private static final String PUBKEY = "0x8f763c496d974ef3e641428bb863a9c7fe85498bcd2f78b26cc1d7ee3a312eb715e9c8736f5b305c68c4d2eb25bd0f78";
    private static final String BLOCK_DATA = "blockDataBase64";
    private static final String SIGNED_BLOCK_DATA = "signedBlockDataBase64";
    private static final String PARENT_ROOT = "0xabcdef1234567890";
    private static final String ATTEST_DATA = "attestDataBase64";
    private static final String SIGNED_ATTEST_DATA = "signedAttestDataBase64";

    private RpcConnection rpcConnection;
    private AttackerClient attackerClient;

    @BeforeEach
    public void setup() {
        rpcConnection = mock(RpcConnection.class);
        attackerClient = new AttackerClient(rpcConnection, 0);
    }

    @Test
    public void shouldCallBlockGetNewParentRoot() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.blockGetNewParentRoot(SLOT, PUBKEY, PARENT_ROOT);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("block_getNewParentRoot"),
                eq(AttackerResponse.class),
                eq(SLOT),
                eq(PUBKEY),
                eq(PARENT_ROOT));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallDelayForReceiveBlock() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.delayForReceiveBlock(SLOT);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("block_delayForReceiveBlock"),
                eq(AttackerResponse.class),
                eq(SLOT));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallBlockBeforeBroadcast() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.blockBeforeBroadcast(SLOT);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("block_beforeBroadCast"),
                eq(AttackerResponse.class),
                eq(SLOT));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallBlockAfterBroadcast() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.blockAfterBroadcast(SLOT);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("block_afterBroadCast"),
                eq(AttackerResponse.class),
                eq(SLOT));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallBlockBeforeSign() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.blockBeforeSign(SLOT, PUBKEY, BLOCK_DATA);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("block_beforeSign"),
                eq(AttackerResponse.class),
                eq(SLOT),
                eq(PUBKEY),
                eq(BLOCK_DATA));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallBlockAfterSign() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.blockAfterSign(SLOT, PUBKEY, SIGNED_BLOCK_DATA);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("block_afterSign"),
                eq(AttackerResponse.class),
                eq(SLOT),
                eq(PUBKEY),
                eq(SIGNED_BLOCK_DATA));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallBlockBeforePropose() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.blockBeforePropose(SLOT, PUBKEY, SIGNED_BLOCK_DATA);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("block_beforePropose"),
                eq(AttackerResponse.class),
                eq(SLOT),
                eq(PUBKEY),
                eq(SIGNED_BLOCK_DATA));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallBlockAfterPropose() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.blockAfterPropose(SLOT, PUBKEY, SIGNED_BLOCK_DATA);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("block_afterPropose"),
                eq(AttackerResponse.class),
                eq(SLOT),
                eq(PUBKEY),
                eq(SIGNED_BLOCK_DATA));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallAttestBeforeBroadcast() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.attestBeforeBroadcast(SLOT);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("attest_beforeBroadCast"),
                eq(AttackerResponse.class),
                eq(SLOT));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallAttestAfterBroadcast() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.attestAfterBroadcast(SLOT);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("attest_afterBroadCast"),
                eq(AttackerResponse.class),
                eq(SLOT));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallAttestBeforeSign() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.attestBeforeSign(SLOT, PUBKEY, ATTEST_DATA);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("attest_beforeSign"),
                eq(AttackerResponse.class),
                eq(SLOT),
                eq(PUBKEY),
                eq(ATTEST_DATA));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallAttestAfterSign() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.attestAfterSign(SLOT, PUBKEY, SIGNED_ATTEST_DATA);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("attest_afterSign"),
                eq(AttackerResponse.class),
                eq(SLOT),
                eq(PUBKEY),
                eq(SIGNED_ATTEST_DATA));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallAttestBeforePropose() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.attestBeforePropose(SLOT, PUBKEY, SIGNED_ATTEST_DATA);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("attest_beforePropose"),
                eq(AttackerResponse.class),
                eq(SLOT),
                eq(PUBKEY),
                eq(SIGNED_ATTEST_DATA));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldCallAttestAfterPropose() throws Exception {
        mockRpcResponse(AttackerCommand.CMD_CONTINUE, "success");

        SafeFuture<AttackerResponse> future = attackerClient.attestAfterPropose(SLOT, PUBKEY, SIGNED_ATTEST_DATA);
        AttackerResponse response = future.get();

        verify(rpcConnection).call(
                eq("attest_afterPropose"),
                eq(AttackerResponse.class),
                eq(SLOT),
                eq(PUBKEY),
                eq(SIGNED_ATTEST_DATA));
        assertThat(response.getCmd()).isEqualTo(AttackerCommand.CMD_CONTINUE);
        assertThat(response.getResult()).isEqualTo("success");
    }

    @Test
    public void shouldHandleDifferentCommandTypes() throws Exception {
        // Test handling of each command type
        for (AttackerCommand command : AttackerCommand.values()) {
            String result = "result for " + command.name();
            mockRpcResponse(command, result);

            SafeFuture<AttackerResponse> future = attackerClient.blockBeforeSign(SLOT, PUBKEY, BLOCK_DATA);
            AttackerResponse response = future.get();

            assertThat(response.getCmd()).isEqualTo(command);
            assertThat(response.getResult()).isEqualTo(result);

            // Test utility methods
            if (command == AttackerCommand.CMD_CONTINUE) {
                assertThat(response.shouldContinue()).isTrue();
            } else {
                assertThat(response.shouldContinue()).isFalse();
            }

            if (command == AttackerCommand.CMD_ABORT) {
                assertThat(response.shouldAbort()).isTrue();
            } else {
                assertThat(response.shouldAbort()).isFalse();
            }
        }
    }

    private void mockRpcResponse(AttackerCommand cmd, String result) {
        AttackerResponse response = new AttackerResponse(cmd, result);
        SafeFuture<AttackerResponse> future = SafeFuture.completedFuture(response);
        when(rpcConnection.call(anyString(), eq(AttackerResponse.class), any())).thenReturn(future);
    }
}
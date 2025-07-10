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
    private static final String BLOCK_MODULE = "block";
    private static final String ATTEST_MODULE = "attest";

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

    /**
     * Get new parent root for block.
     *
     * @param slot The slot number
     * @param pubkey The validator public key
     * @param parentRoot The parent root
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> blockGetNewParentRoot(long slot, String pubkey, String parentRoot) {
        return rpcConnection.call(
                BLOCK_MODULE + "_getNewParentRoot",
                AttackerResponse.class,
                slot,
                pubkey,
                parentRoot);
    }

    /**
     * Delay for receive block.
     *
     * @param slot The slot number
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> delayForReceiveBlock(long slot) {
        return rpcConnection.call(
                BLOCK_MODULE + "_delayForReceiveBlock",
                AttackerResponse.class,
                slot);
    }

    /**
     * Before block broadcast.
     *
     * @param slot The slot number
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> blockBeforeBroadcast(long slot) {
        return rpcConnection.call(
                BLOCK_MODULE + "_beforeBroadCast",
                AttackerResponse.class,
                slot);
    }

    /**
     * After block broadcast.
     *
     * @param slot The slot number
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> blockAfterBroadcast(long slot) {
        return rpcConnection.call(
                BLOCK_MODULE + "_afterBroadCast",
                AttackerResponse.class,
                slot);
    }

    /**
     * Before sign block.
     *
     * @param slot The slot number
     * @param pubkey The validator public key
     * @param blockDataBase64 The block data in base64 encoding
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> blockBeforeSign(long slot, String pubkey, String blockDataBase64) {
        return rpcConnection.call(
                BLOCK_MODULE + "_beforeSign",
                AttackerResponse.class,
                slot,
                pubkey,
                blockDataBase64);
    }

    /**
     * After sign block.
     *
     * @param slot The slot number
     * @param pubkey The validator public key
     * @param signedBlockDataBase64 The signed block data in base64 encoding
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> blockAfterSign(long slot, String pubkey, String signedBlockDataBase64) {
        return rpcConnection.call(
                BLOCK_MODULE + "_afterSign",
                AttackerResponse.class,
                slot,
                pubkey,
                signedBlockDataBase64);
    }

    /**
     * Before propose block.
     *
     * @param slot The slot number
     * @param pubkey The validator public key
     * @param signedBlockDataBase64 The signed block data in base64 encoding
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> blockBeforePropose(long slot, String pubkey, String signedBlockDataBase64) {
        return rpcConnection.call(
                BLOCK_MODULE + "_beforePropose",
                AttackerResponse.class,
                slot,
                pubkey,
                signedBlockDataBase64);
    }

    /**
     * After propose block.
     *
     * @param slot The slot number
     * @param pubkey The validator public key
     * @param signedBlockDataBase64 The signed block data in base64 encoding
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> blockAfterPropose(long slot, String pubkey, String signedBlockDataBase64) {
        return rpcConnection.call(
                BLOCK_MODULE + "_afterPropose",
                AttackerResponse.class,
                slot,
                pubkey,
                signedBlockDataBase64);
    }

    /**
     * Before attestation broadcast.
     *
     * @param slot The slot number
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> attestBeforeBroadcast(long slot) {
        return rpcConnection.call(
                ATTEST_MODULE + "_beforeBroadCast",
                AttackerResponse.class,
                slot);
    }

    /**
     * After attestation broadcast.
     *
     * @param slot The slot number
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> attestAfterBroadcast(long slot) {
        return rpcConnection.call(
                ATTEST_MODULE + "_afterBroadCast",
                AttackerResponse.class,
                slot);
    }

    /**
     * Before signing attestation.
     *
     * @param slot The slot number
     * @param pubkey The validator public key
     * @param attestDataBase64 The attestation data in base64 encoding
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> attestBeforeSign(
            long slot,
            String pubkey,
            String attestDataBase64) {
        return rpcConnection.call(
                ATTEST_MODULE + "_beforeSign",
                AttackerResponse.class,
                slot,
                pubkey,
                attestDataBase64);
    }

    /**
     * After signing attestation.
     *
     * @param slot The slot number
     * @param pubkey The validator public key
     * @param signedAttestDataBase64 The signed attestation data in base64 encoding
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> attestAfterSign(
            long slot,
            String pubkey,
            String signedAttestDataBase64) {
        return rpcConnection.call(
                ATTEST_MODULE + "_afterSign",
                AttackerResponse.class,
                slot,
                pubkey,
                signedAttestDataBase64);
    }

    /**
     * Before proposing attestation.
     *
     * @param slot The slot number
     * @param pubkey The validator public key
     * @param signedAttestDataBase64 The signed attestation data in base64 encoding
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> attestBeforePropose(
            long slot,
            String pubkey,
            String signedAttestDataBase64) {
        return rpcConnection.call(
                ATTEST_MODULE + "_beforePropose",
                AttackerResponse.class,
                slot,
                pubkey,
                signedAttestDataBase64);
    }

    /**
     * After proposing attestation.
     *
     * @param slot The slot number
     * @param pubkey The validator public key
     * @param signedAttestDataBase64 The signed attestation data in base64 encoding
     * @return A future that completes with the response
     */
    public SafeFuture<AttackerResponse> attestAfterPropose(
            long slot,
            String pubkey,
            String signedAttestDataBase64) {
        return rpcConnection.call(
                ATTEST_MODULE + "_afterPropose",
                AttackerResponse.class,
                slot,
                pubkey,
                signedAttestDataBase64);
    }
}
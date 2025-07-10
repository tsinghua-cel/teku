package tech.pegasys.teku.attacker.client;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AttackerResponse defines the response from the attacker client API.
 * This matches the Go implementation in tsinghua-cel/attacker-client-go/client/type.go.
 */
public class AttackerResponse {
    @JsonProperty("cmd")
    private AttackerCommand cmd;

    @JsonProperty("result")
    private String result;

    public AttackerResponse() {
        // Default constructor for Jackson deserialization
    }

    public AttackerResponse(AttackerCommand cmd, String result) {
        this.cmd = cmd;
        this.result = result;
    }

    public AttackerCommand getCmd() {
        return cmd;
    }

    public String getResult() {
        return result;
    }

    /**
     * Convenience method to check if the command is CMD_CONTINUE.
     *
     * @return true if the command is CMD_CONTINUE, false otherwise
     */
    public boolean shouldContinue() {
        return cmd == AttackerCommand.CMD_CONTINUE;
    }

    /**
     * Convenience method to check if the command is CMD_ABORT.
     *
     * @return true if the command is CMD_ABORT, false otherwise
     */
    public boolean shouldAbort() {
        return cmd == AttackerCommand.CMD_ABORT;
    }

    @Override
    public String toString() {
        return "AttackerResponse{" +
                "cmd=" + cmd +
                ", result='" + result + '\'' +
                '}';
    }
}
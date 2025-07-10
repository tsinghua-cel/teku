package tech.pegasys.teku.attacker.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * AttackerCommand defines the command types that can be returned by the attacker client API.
 * This matches the Go implementation in tsinghua-cel/attacker-client-go/client/type.go.
 */
public enum AttackerCommand {
    CMD_NULL(0),
    CMD_CONTINUE(1),
    CMD_RETURN(2),
    CMD_ABORT(3),
    CMD_SKIP(4),
    CMD_ROLE_TO_NORMAL(5),   // Role changes to normal node
    CMD_ROLE_TO_ATTACKER(6), // Role changes to attacker
    CMD_EXIT(7),
    CMD_UPDATE_STATE(8),
    CMP_DELAY_BROAD_CAST(9);

    private final int value;

    AttackerCommand(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    @JsonCreator
    public static AttackerCommand fromValue(int value) {
        for (AttackerCommand command : AttackerCommand.values()) {
            if (command.value == value) {
                return command;
            }
        }
        throw new IllegalArgumentException("Unknown AttackerCommand value: " + value);
    }
}
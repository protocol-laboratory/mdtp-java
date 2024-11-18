package io.github.protocol.mdtp.common.model;

public enum MessageType {
    REQUEST(0),

    RESPONSE(1),

    NOTIFY(2);

    private final int code;

    MessageType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

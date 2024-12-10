package io.github.protocol.mdtp.common.model;

import lombok.Getter;

@Getter
public enum ServiceGroup {

    DISCOVERY_SERVICE(1, "DISCOVERY_SERVICE"),

    SECURITY_SERVICE(2, "SECURITY_SERVICE"),

    CONNECTION_SERVICE(3, "CONNECTION_SERVICE"),

    BILLING_SERVICE(4, "BILLING_SERVICE"),

    SETTING_SERVICE(5, "SETTING_SERVICE"),

    METHOD_SERVICE(6, "METHOD_SERVICE");

    private final int code;

    private final String description;

    ServiceGroup(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ServiceGroup fromCode(int value) {
        for (ServiceGroup group : ServiceGroup.values()) {
            if (group.getCode() == value) {
                return group;
            }
        }
        throw new IllegalArgumentException("Invalid ServiceGroup code: " + value);
    }
}

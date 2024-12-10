package io.github.protocol.mdtp.common.model;

import lombok.Getter;

@Getter
public enum DiscoveryServiceCode {
    DEVICE_DISCOVERY(1, "DEVICE_DISCOVERY"),

    DEVICE_INFO_QUERY(2, "DEVICE_INFO_QUERY");

    private final int code;
    private final String description;

    DiscoveryServiceCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static DiscoveryServiceCode fromCode(int value) {
        for (DiscoveryServiceCode serviceCode : DiscoveryServiceCode.values()) {
            if (serviceCode.getCode() == value) {
                return serviceCode;
            }
        }
        throw new IllegalArgumentException("Invalid DiscoveryServiceCode: " + value);
    }
}

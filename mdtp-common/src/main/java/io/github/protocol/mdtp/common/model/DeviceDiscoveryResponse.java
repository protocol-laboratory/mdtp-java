package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.nio.charset.StandardCharsets;

@Data
public class DeviceDiscoveryResponse {
    private short messageHeader;

    private short requestId;

    private short responseId;

    private byte mask;

    private byte deviceStatus;

    private byte addressCount;

    private String[] addresses;

    private short port;

    private int deviceType;

    private byte[] uniqueId;

    private String deviceName;

    public void writeByteBuf(ByteBuf buffer) {
        buffer.writeShort(messageHeader);
        buffer.writeShort(requestId);
        buffer.writeShort(responseId);
        buffer.writeByte(mask);
        buffer.writeByte(deviceStatus);
        buffer.writeByte(addressCount);

        for (String address : addresses) {
            byte[] addressBytes = address.getBytes(StandardCharsets.UTF_8);
            buffer.writeBytes(addressBytes);
        }

        buffer.writeShort(port);
        buffer.writeInt(deviceType);

        if (uniqueId != null) {
            buffer.writeBytes(uniqueId);
        }

        if (deviceName != null) {
            byte[] nameBytes = deviceName.getBytes(StandardCharsets.UTF_8);
            buffer.writeBytes(nameBytes);
        }
    }
}

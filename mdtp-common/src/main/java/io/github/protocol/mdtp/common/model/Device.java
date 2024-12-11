package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {
    private byte mask;

    private byte deviceStatus;

    private byte addressCount;

    private List<Address> addresses;

    private short port;

    private int deviceType;

    private byte[] uniqueId;

    private String deviceName;

    public void writeByteBuf(ByteBuf buffer) {
        buffer.writeByte(mask);
        buffer.writeByte(deviceStatus);
        buffer.writeByte(addressCount);

        for (Address address : addresses) {
            address.writeByteBuf(buffer);
        }

        buffer.writeShort(port);
        buffer.writeInt(deviceType);

        if (uniqueId != null) {
            buffer.writeShort(uniqueId.length);
            buffer.writeBytes(uniqueId);
        } else {
            buffer.writeShort(0);
        }

        if (deviceName != null) {
            byte[] nameBytes = deviceName.getBytes(StandardCharsets.UTF_8);
            buffer.writeShort(nameBytes.length);
            buffer.writeBytes(nameBytes);
        } else {
            buffer.writeShort(0);
        }
    }

    public static Device readByteBuf(ByteBuf buffer) {
        Device device = new Device();

        device.mask = buffer.readByte();
        device.deviceStatus = buffer.readByte();
        device.addressCount = buffer.readByte();

        device.addresses = new ArrayList<>();
        for (int i = 0; i < device.addressCount; i++) {
            device.addresses.add(Address.readByteBuf(buffer));
        }

        device.port = buffer.readShort();
        device.deviceType = buffer.readInt();

        int uniqueIdLength = buffer.readShort();
        if (uniqueIdLength > 0) {
            device.uniqueId = new byte[uniqueIdLength];
            buffer.readBytes(device.uniqueId);
        } else {
            device.uniqueId = null;
        }

        int deviceNameLength = buffer.readShort();
        if (deviceNameLength > 0) {
            byte[] nameBytes = new byte[deviceNameLength];
            buffer.readBytes(nameBytes);
            device.deviceName = new String(nameBytes, StandardCharsets.UTF_8);
        } else {
            device.deviceName = null;
        }

        return device;
    }
}

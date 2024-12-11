package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.Address;
import io.github.protocol.mdtp.common.model.Device;
import io.github.protocol.mdtp.common.model.DeviceDiscoveryResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceDiscoveryResponseTest {

    private DeviceDiscoveryResponse response;

    @BeforeEach
    void setUp() {
        response = new DeviceDiscoveryResponse();
        response.setRequestId((short) 200);
        response.setResponseId((short) 300);
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(Address.IPV4_TYPE, new byte[]{(byte) 192,(byte) 168,1,2} ));
        addresses.add(new Address(Address.IPV4_TYPE, new byte[]{(byte) 192,(byte) 168,1,1} ));
        Device device = Device.builder()
                .mask((byte) 0x1F)
                .deviceStatus((byte) 0x01)
                .addressCount((byte) 2)
                .addresses(addresses)
                .port((short) 8080)
                .deviceType(1)
                .uniqueId(new byte[]{0x01, 0x02, 0x03, 0x04})
                .deviceName("TestDevice").build();
        response.setDevice(device);
    }

    @Test
    void testToByteBuf() {
        ByteBuf buffer = Unpooled.buffer();

        response.writeByteBuf(buffer);

        assertEquals(100, buffer.readShort());
        assertEquals(200, buffer.readShort());
        assertEquals(300, buffer.readShort());
        assertEquals(0x1F, buffer.readByte());
        assertEquals(0x01, buffer.readByte());
        assertEquals(2, buffer.readByte());

        byte[] addressBytes1 = new byte[11];
        buffer.readBytes(addressBytes1);
        assertEquals("192.168.1.1", new String(addressBytes1, StandardCharsets.UTF_8));

        byte[] addressBytes2 = new byte[11];
        buffer.readBytes(addressBytes2);
        assertEquals("192.168.1.2", new String(addressBytes2, StandardCharsets.UTF_8));

        assertEquals(8080, buffer.readShort());
        assertEquals(12345, buffer.readInt());

        byte[] uniqueId = new byte[4];
        buffer.readBytes(uniqueId);
        assertArrayEquals(new byte[]{0x01, 0x02, 0x03, 0x04}, uniqueId);

        byte[] nameBytes = new byte[10];
        buffer.readBytes(nameBytes);
        assertEquals("TestDevice", new String(nameBytes, StandardCharsets.UTF_8));

        buffer.release();
    }
}

package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.Address;
import io.github.protocol.mdtp.common.model.Device;
import io.github.protocol.mdtp.common.model.DeviceDiscoveryResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    void testWriteByteBuf() {
        ByteBuf buffer = Unpooled.buffer();
        response.writeByteBuf(buffer);

        assertEquals((short) 1033, buffer.readShort());
        assertEquals((short) 200, buffer.readShort());
        assertEquals((short) 300, buffer.readShort());
    }

    @Test
    void testReadFromBuffer() {
        ByteBuf buffer = Unpooled.buffer();
        response.writeByteBuf(buffer);
        buffer.readShort();
        DeviceDiscoveryResponse readResponse = DeviceDiscoveryResponse.readFromBuffer(buffer);
        assertEquals(response.getRequestId(), readResponse.getRequestId());
        assertEquals(response.getResponseId(), readResponse.getResponseId());
        assertEquals(response.getDevice().getDeviceName(), readResponse.getDevice().getDeviceName());
    }
}

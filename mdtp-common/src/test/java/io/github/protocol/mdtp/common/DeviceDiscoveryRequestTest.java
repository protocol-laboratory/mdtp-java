package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.DeviceDiscoveryRequest;
import io.github.protocol.mdtp.common.model.MessageBodyHeader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeviceDiscoveryRequestTest {
    private DeviceDiscoveryRequest request;

    @BeforeEach
    void setUp() {
        request = new DeviceDiscoveryRequest();
        request.setRequestId((short) 123);
        request.setMask((byte) 0x1F);
        request.setDeviceTypeCount((byte) 3);
        request.setDeviceTypes(new int[]{1001, 1002, 1003});
    }

    @Test
    void testToByteBuf() {
        ByteBuf buffer = Unpooled.buffer();

        request.writeByteBuf(buffer);
        assertEquals(MessageBodyHeader.DEVICE_DISCOVERY_REQUEST.toShort(), buffer.readShort());
        assertEquals( 123, buffer.readShort());
        assertEquals(0x1F, buffer.readByte());
        assertEquals(3, buffer.readByte());
        assertEquals(1001, buffer.readInt());
        assertEquals(1002, buffer.readInt());
        assertEquals(1003, buffer.readInt());

        buffer.release();
    }

    @Test
    void testFromByteBuf() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeShort(123);
        buffer.writeByte(0x1F);
        buffer.writeByte(3);
        buffer.writeInt(1001);
        buffer.writeInt(1002);
        buffer.writeInt(1003);

        DeviceDiscoveryRequest result = DeviceDiscoveryRequest.fromByteBuf(buffer);

        assertEquals(123, result.getRequestId());
        assertEquals(0x1F, result.getMask());
        assertEquals(3, result.getDeviceTypeCount());
        assertArrayEquals(new int[]{1001, 1002, 1003}, result.getDeviceTypes());

        buffer.release();
    }
}

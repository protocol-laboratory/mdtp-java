package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.CDATHeader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CDATHeaderTest {
    private CDATHeader cdatHeader;

    @BeforeEach
    void setUp() {
        cdatHeader = new CDATHeader(
                (byte) 0,
                (byte) 2,
                (short) 100,
                123456789L,
                (byte) 0x1F,
                42,
                7
        );
    }

    @Test
    void testToByteBuf() {
        ByteBuf buffer = Unpooled.buffer();

        cdatHeader.writeByteBuf(buffer);

        assertEquals(0, buffer.readByte());
        assertEquals(2, buffer.readByte());
        assertEquals(100, buffer.readShort());
        assertEquals(123456789L, buffer.readLong());
        assertEquals(0x1F, buffer.readByte());
        assertEquals(42, buffer.readInt());
        assertEquals(7, buffer.readInt());

        buffer.release();
    }

    @Test
    void testFromByteBuf() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeByte(0);
        buffer.writeByte(2);
        buffer.writeShort(100);
        buffer.writeLong(123456789L);
        buffer.writeByte((byte) 0x1F);
        buffer.writeInt(42);
        buffer.writeInt(7);

        CDATHeader headerFromBuffer = CDATHeader.readByteBuf(buffer);

        assertEquals(cdatHeader.getFormatType(), headerFromBuffer.getFormatType());
        assertEquals(cdatHeader.getProtocolVersion(), headerFromBuffer.getProtocolVersion());
        assertEquals(cdatHeader.getMessageLength(), headerFromBuffer.getMessageLength());
        assertEquals(cdatHeader.getTimestamp(), headerFromBuffer.getTimestamp());
        assertEquals(cdatHeader.getFlags(), headerFromBuffer.getFlags());
        assertEquals(cdatHeader.getSequenceNumber(), headerFromBuffer.getSequenceNumber());
        assertEquals(cdatHeader.getLogicalChannelId(), headerFromBuffer.getLogicalChannelId());

        buffer.release();
    }
}

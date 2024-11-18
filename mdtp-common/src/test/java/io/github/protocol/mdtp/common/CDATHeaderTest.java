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
                (byte) 1,
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

        cdatHeader.toByteBuf(buffer);

        assertEquals(1, buffer.readByte());
        assertEquals(2, buffer.readByte());
        assertEquals(100, buffer.readShort());
        assertEquals(123456789L, buffer.readLong());
        assertEquals(0x1F, buffer.readByte());
        assertEquals(42, buffer.readInt());
        assertEquals(7, buffer.readInt());

        buffer.release();
    }
}

package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.SecurityHeader;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecurityHeaderTest {
    private SecurityHeader securityHeader;

    @BeforeEach
    void setUp() {
        securityHeader = new SecurityHeader();
        securityHeader.setEncryptionData(new byte[]{0x01, 0x02, 0x03, 0x04});
    }

    @Test
    void testToByteBuf() {
        ByteBuf buffer = Unpooled.buffer();

        securityHeader.writeByteBuf(buffer);

        assertEquals(1, buffer.readInt());
        assertEquals(2, buffer.readInt());
        assertEquals(3, buffer.readInt());
        assertEquals(4, buffer.readInt());

        buffer.release();
    }
}

package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.AbstractMessageBody;
import io.github.protocol.mdtp.common.model.CDATHeader;
import io.github.protocol.mdtp.common.model.MdtpPacket;
import io.github.protocol.mdtp.common.model.SecurityHeader;
import io.github.protocol.mdtp.common.model.Signature;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MdtpPacketTest {
    private MdtpPacket mdtpPacket;
    private CDATHeader mockHeader;
    private SecurityHeader mockSecurityHeader;
    private AbstractMessageBody mockBody;
    private Signature mockSignature;

    @BeforeEach
    void setUp() {
        mockHeader = mock(CDATHeader.class);
        mockSecurityHeader = mock(SecurityHeader.class);
        mockBody = mock(AbstractMessageBody.class);
        mockSignature = mock(Signature.class);

        mdtpPacket = new MdtpPacket();
        mdtpPacket.setHeader(mockHeader);
        mdtpPacket.setSecurityHeader(mockSecurityHeader);
        mdtpPacket.setBody(mockBody);
        mdtpPacket.setSignature(mockSignature);
    }

    @Test
    void testToByteBuf() {
        ByteBuf buffer = Unpooled.buffer();

        mdtpPacket.toByteBuf(buffer);

        verify(mockHeader).toByteBuf(buffer);
        verify(mockSecurityHeader).toByteBuf(buffer);
        verify(mockBody).toByteBuf(buffer);

        buffer.release();
    }


}

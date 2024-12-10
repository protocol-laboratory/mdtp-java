package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.codec.MdtpDecoder;
import io.github.protocol.mdtp.common.model.CDATHeader;
import io.github.protocol.mdtp.common.model.MdtpPacket;
import io.github.protocol.mdtp.common.model.MessageBodyHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MdtpDecoderTest extends  MdtpDecoder {

    private ChannelHandlerContext ctx;
    private ByteBuf in;
    private List<Object> out;

    @BeforeEach
    public void setUp() {
        ctx = mock(ChannelHandlerContext.class);
        in = mock(ByteBuf.class);
        out = new ArrayList<>();
        when(in.readShort()).thenReturn(MessageBodyHeader.DEVICE_DISCOVERY_REQUEST.toShort());
    }

    @Test
    public void testDecode() throws Exception {
        CDATHeader mockHeader = mock(CDATHeader.class);
        try (MockedStatic<CDATHeader> mockedCDATHeader = Mockito.mockStatic(CDATHeader.class)) {
            mockedCDATHeader.when(() -> CDATHeader.readByteBuf(in)).thenReturn(mockHeader);
            decode(ctx, in, out);
            assertEquals(1, out.size());
            MdtpPacket mdtpPacket = (MdtpPacket) out.get(0);
            assertEquals(mockHeader, mdtpPacket.getHeader());
        }
    }
}

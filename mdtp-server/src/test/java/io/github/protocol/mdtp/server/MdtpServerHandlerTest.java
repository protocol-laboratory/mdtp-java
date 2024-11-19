package io.github.protocol.mdtp.server;

import io.github.protocol.mdtp.common.model.MdtpPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class MdtpServerHandlerTest extends MdtpServerHandler {

    private ChannelHandlerContext ctx;
    private EmbeddedChannel channel;

    @BeforeEach
    public void setUp() {
        ctx = Mockito.mock(ChannelHandlerContext.class);
        channel = new EmbeddedChannel();
    }

    @Test
    public void testChannelRead0() throws Exception {
        MdtpPacket packet = Mockito.mock(MdtpPacket.class);
        Mockito.when(packet.toString()).thenReturn("Mocked MdtpPacket");
        channelRead0(ctx, packet);
    }

    @Test
    public void testExceptionCaught() throws Exception {
        Throwable cause = new Throwable();
        exceptionCaught(ctx, cause);
        verify(ctx).close();
    }
}

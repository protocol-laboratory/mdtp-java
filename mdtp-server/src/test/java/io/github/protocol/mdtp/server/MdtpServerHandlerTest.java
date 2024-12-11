package io.github.protocol.mdtp.server;

import io.github.protocol.mdtp.common.handler.MessageBodyHandler;
import io.github.protocol.mdtp.common.handler.MessageHandlerFactory;
import io.github.protocol.mdtp.common.model.DeviceDiscoveryRequest;
import io.github.protocol.mdtp.common.model.MdtpPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class MdtpServerHandlerTest extends MdtpServerHandler {

    private MdtpServerHandler handler;
    private ChannelHandlerContext ctx;
    private EmbeddedChannel channel;

    @BeforeEach
    public void setUp() {
        ctx = Mockito.mock(ChannelHandlerContext.class);
        channel = new EmbeddedChannel();
        handler = new MdtpServerHandler();
    }

    @Test
    public void testChannelRead0() throws Exception {
        MdtpPacket packet = Mockito.mock(MdtpPacket.class);
        Mockito.when(packet.getBody()).thenReturn(new DeviceDiscoveryRequest());

        MessageBodyHandler messageBodyHandler = Mockito.mock(MessageBodyHandler.class);
        try (MockedStatic<MessageHandlerFactory> mockedStatic = Mockito.mockStatic(MessageHandlerFactory.class)) {
            mockedStatic.when(() -> MessageHandlerFactory.getHandler(any())).thenReturn(messageBodyHandler);
            handler.channelRead0(ctx, packet);
            verify(messageBodyHandler).handle(ctx, packet);
        }
    }

    @Test
    public void testExceptionCaught() throws Exception {
        Throwable cause = new RuntimeException("Test exception");
        handler.exceptionCaught(ctx, cause);
        verify(ctx).close();
    }
}

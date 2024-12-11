package io.github.protocol.mdtp.server;

import io.github.protocol.mdtp.common.handler.MessageBodyHandler;
import io.github.protocol.mdtp.common.handler.MessageHandlerFactory;
import io.github.protocol.mdtp.common.model.AbstractMessageBody;
import io.github.protocol.mdtp.common.model.MdtpPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MdtpServerHandler extends SimpleChannelInboundHandler<MdtpPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MdtpPacket packet) throws Exception {
        AbstractMessageBody messageBody = packet.getBody();
        MessageBodyHandler handler = MessageHandlerFactory.getHandler(messageBody.getMessageBodyHeader());
        handler.handle(ctx, packet);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

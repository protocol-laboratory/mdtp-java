package io.github.protocol.mdtp.common.handler;

import io.github.protocol.mdtp.common.model.MdtpPacket;
import io.netty.channel.ChannelHandlerContext;

public interface MessageBodyHandler {

    void handle (ChannelHandlerContext ctx, MdtpPacket mdtpPacket);
}

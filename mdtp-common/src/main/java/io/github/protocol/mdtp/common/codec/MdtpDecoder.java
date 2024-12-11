package io.github.protocol.mdtp.common.codec;


import io.github.protocol.mdtp.common.model.AbstractMessageBody;
import io.github.protocol.mdtp.common.model.CDATHeader;
import io.github.protocol.mdtp.common.model.MdtpPacket;
import io.github.protocol.mdtp.common.model.MessageBodyHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MdtpDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        MdtpPacket mdtpPacket = new MdtpPacket();
        CDATHeader header = CDATHeader.readByteBuf(in);

        MessageBodyHeader messageBodyHeader = MessageBodyHeader.readByteBuf(in);
        MessageBodyDecoder messageDecode = MessageDecoderFactory.getDecoder(messageBodyHeader);
        AbstractMessageBody messageBody = messageDecode.handle(in);
        mdtpPacket.setHeader(header);
        mdtpPacket.setBody(messageBody);

        out.add(mdtpPacket);
        log.info("decode packet success: {}", mdtpPacket);
    }
}

package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;

@Data
public class MdtpPacket {

    private CDATHeader header;

    private SecurityHeader securityHeader;

    private AbstractMessageBody body;

    private Signature signature;

    public ByteBuf toByteBuf() {
        ByteBuf buffer = Unpooled.buffer();
        header.writeByteBuf(buffer);
        if (securityHeader != null) {
            securityHeader.writeByteBuf(buffer);
        }
        if (body != null) {
            body.writeByteBuf(buffer);
        }
        return buffer;
    }
}

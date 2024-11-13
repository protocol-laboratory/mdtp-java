package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class MdtpPacket {

    private CDATHeader header;

    private SecurityHeader securityHeader;

    private AbstractMessageBody body;

    private Signature signature;

    public ByteBuf toByteBuf(ByteBuf buffer) {
        header.toByteBuf(buffer);
        if (securityHeader != null) {
            securityHeader.toByteBuf(buffer);
        }
        if (body != null) {
            body.toByteBuf(buffer);
        }
        return buffer;
    }
}

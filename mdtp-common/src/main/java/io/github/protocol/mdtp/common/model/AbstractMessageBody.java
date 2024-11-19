package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.UUID;

@Data
public abstract class AbstractMessageBody {
    private MessageBodyHeader messageBodyHeader;

    public short generateRequestId() {
        UUID uuid = UUID.randomUUID();
        return (short) (uuid.getLeastSignificantBits() & 0xFFFF);
    }

    public void writeByteBuf(ByteBuf buffer) {
        messageBodyHeader.writeByteBuf(buffer);
    }
}

package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.UUID;

@Data
public abstract class AbstractMessageBody {
    private MessageBodyHeader messageBodyHeader;

    protected AbstractMessageBody () {
    }

    protected AbstractMessageBody (MessageBodyHeader messageBodyHeader) {
        this.messageBodyHeader = messageBodyHeader;
    }

    public short generateId() {
        UUID uuid = UUID.randomUUID();
        return (short) (uuid.getLeastSignificantBits() & 0x7FFF);
    }

    public void writeByteBuf(ByteBuf buffer) {
        messageBodyHeader.writeByteBuf(buffer);
    }
}

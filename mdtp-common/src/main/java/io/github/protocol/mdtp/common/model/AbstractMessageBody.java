package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.UUID;

@Data
public abstract class AbstractMessageBody {
    private short messageBodyHeader;

    public void setMessageBodyHeader(MessageType messageType, ServiceGroup serviceGroup, DiscoveryServiceCode serviceCode) {
        this.messageBodyHeader = 0;
        this.messageBodyHeader |= (short) (messageType.getCode() & 0b111);
        this.messageBodyHeader |= (short) ((serviceGroup.getCode() & 0b1111111) << 3);
        this.messageBodyHeader |= (short) ((serviceCode.getCode() & 0b111111) << 10);
    }

    public short generateRequestId() {
        UUID uuid = UUID.randomUUID();
        return (short) (uuid.getLeastSignificantBits() & 0xFFFF);
    }

    public ByteBuf toByteBuf(ByteBuf buffer) {
        buffer.writeShort(messageBodyHeader);
        return buffer;
    }
}

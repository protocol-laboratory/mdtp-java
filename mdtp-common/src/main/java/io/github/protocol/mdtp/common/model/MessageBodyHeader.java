package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MessageBodyHeader {
    private MessageType messageType;

    private ServiceGroup serviceGroup;

    private DiscoveryServiceCode serviceCode;

    public static final MessageBodyHeader DEVICE_DISCOVERY_REQUEST =
            new MessageBodyHeader(MessageType.REQUEST, ServiceGroup.DISCOVERY_SERVICE, DiscoveryServiceCode.DEVICE_DISCOVERY);

    public static final MessageBodyHeader DEVICE_DISCOVERY_RESPONSE =
            new MessageBodyHeader(MessageType.RESPONSE, ServiceGroup.DISCOVERY_SERVICE, DiscoveryServiceCode.DEVICE_DISCOVERY);

    public short toShort() {
        short messageBodyHeader = 0;
        messageBodyHeader |= (short) (this.messageType.getCode() & 0b111);
        messageBodyHeader |= (short) ((this.serviceGroup.getCode() & 0b1111111) << 3);
        messageBodyHeader |= (short) ((this.serviceCode.getCode() & 0b111111) << 10);
        return messageBodyHeader;
    }

    public void writeByteBuf(ByteBuf buffer) {
        buffer.writeShort(toShort());
    }

    public static MessageBodyHeader readByteBuf(ByteBuf buffer) {
        short messageBodyHeader = buffer.readShort();
        MessageType messageType = MessageType.fromCode((short) (messageBodyHeader & 0b111));
        ServiceGroup serviceGroup = ServiceGroup.fromCode((short) ((messageBodyHeader >> 3) & 0b1111111));
        DiscoveryServiceCode serviceCode = DiscoveryServiceCode.fromCode((short) ((messageBodyHeader >> 10) & 0b111111));
        return new MessageBodyHeader(messageType, serviceGroup, serviceCode);
    }
}

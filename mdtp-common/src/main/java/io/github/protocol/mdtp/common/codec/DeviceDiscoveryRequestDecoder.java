package io.github.protocol.mdtp.common.codec;

import io.github.protocol.mdtp.common.model.AbstractMessageBody;
import io.github.protocol.mdtp.common.model.DeviceDiscoveryRequest;
import io.netty.buffer.ByteBuf;

public class DeviceDiscoveryRequestDecoder implements MessageBodyDecoder {
    @Override
    public AbstractMessageBody handle(ByteBuf in) {
        return DeviceDiscoveryRequest.fromByteBuf(in);
    }
}

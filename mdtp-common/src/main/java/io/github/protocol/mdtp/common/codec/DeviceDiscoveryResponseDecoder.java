package io.github.protocol.mdtp.common.codec;

import io.github.protocol.mdtp.common.model.AbstractMessageBody;
import io.github.protocol.mdtp.common.model.DeviceDiscoveryResponse;
import io.netty.buffer.ByteBuf;

public class DeviceDiscoveryResponseDecoder implements MessageBodyDecoder {
    @Override
    public AbstractMessageBody handle(ByteBuf in) {
        return DeviceDiscoveryResponse.readFromBuffer(in);
    }

}

package io.github.protocol.mdtp.common.codec;

import io.github.protocol.mdtp.common.model.MessageBodyHeader;

import java.util.HashMap;
import java.util.Map;

public class MessageDecoderFactory {

    private static final Map<Short, MessageBodyDecoder> decoders = new HashMap<>();

    static {
        decoders.put(MessageBodyHeader.DEVICE_DISCOVERY_REQUEST.toShort(), new DeviceDiscoveryRequestDecoder());
    }

    public static MessageBodyDecoder getDecoder(MessageBodyHeader header) {
        return decoders.get(header.toShort());
    }
}

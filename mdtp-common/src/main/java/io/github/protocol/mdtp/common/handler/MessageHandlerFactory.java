package io.github.protocol.mdtp.common.handler;


import io.github.protocol.mdtp.common.model.MessageBodyHeader;

import java.util.HashMap;
import java.util.Map;

public class MessageHandlerFactory {
    private static final Map<Short, MessageBodyHandler> handlers = new HashMap<>();

    static {
        handlers.put(MessageBodyHeader.DEVICE_DISCOVERY_REQUEST.toShort(), new DeviceDiscoveryRequestHandler());
    }

    public static MessageBodyHandler getHandler(MessageBodyHeader header) {
        return handlers.get(header.toShort());
    }
}

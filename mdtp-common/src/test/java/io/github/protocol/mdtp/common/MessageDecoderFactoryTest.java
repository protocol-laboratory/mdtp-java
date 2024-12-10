package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.codec.DeviceDiscoveryRequestDecoder;
import io.github.protocol.mdtp.common.codec.MessageBodyDecoder;
import io.github.protocol.mdtp.common.codec.MessageDecoderFactory;
import io.github.protocol.mdtp.common.model.MessageBodyHeader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageDecoderFactoryTest {
    @Test
    public void testGetDecoder_ValidHeader() {
        MessageBodyHeader header = MessageBodyHeader.DEVICE_DISCOVERY_REQUEST;

        MessageBodyDecoder decoder = MessageDecoderFactory.getDecoder(header);

        assertNotNull(decoder);
        assertTrue(decoder instanceof DeviceDiscoveryRequestDecoder);
    }
}

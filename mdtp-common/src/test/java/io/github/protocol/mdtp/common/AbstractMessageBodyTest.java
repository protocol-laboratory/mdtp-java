package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.AbstractMessageBody;
import io.github.protocol.mdtp.common.model.DiscoveryServiceCode;
import io.github.protocol.mdtp.common.model.MessageType;
import io.github.protocol.mdtp.common.model.ServiceGroup;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractMessageBodyTest {

    private AbstractMessageBody messageBody;

    @BeforeEach
    void setUp() {
        messageBody = new AbstractMessageBody(){};
    }

    @Test
    void testSetMessageBodyHeader() {
        MessageType messageType = mock(MessageType.class);
        ServiceGroup serviceGroup = mock(ServiceGroup.class);
        DiscoveryServiceCode serviceCode = mock(DiscoveryServiceCode.class);

        when(messageType.getCode()).thenReturn(1);
        when(serviceGroup.getCode()).thenReturn(2);
        when(serviceCode.getCode()).thenReturn( 3);

        messageBody.setMessageBodyHeader(messageType, serviceGroup, serviceCode);

        short expectedHeader = (short) ((1 & 0b111) | ((2 & 0b1111111) << 3) | ((3 & 0b111111) << 10));

        assertEquals(expectedHeader, messageBody.getMessageBodyHeader());
    }

    @Test
    void testGenerateRequestId() {
        short requestId1 = messageBody.generateRequestId();
        short requestId2 = messageBody.generateRequestId();

        assertEquals(Short.class, ((Object) requestId1).getClass());
        assertEquals(Short.class, ((Object) requestId2).getClass());
        assertNotEquals(requestId1, requestId2);
    }

    @Test
    void testToByteBuf() {
        ByteBuf buffer = Unpooled.buffer();

        MessageType messageType = mock(MessageType.class);
        ServiceGroup serviceGroup = mock(ServiceGroup.class);
        DiscoveryServiceCode serviceCode = mock(DiscoveryServiceCode.class);

        when(messageType.getCode()).thenReturn(1);
        when(serviceGroup.getCode()).thenReturn(2);
        when(serviceCode.getCode()).thenReturn( 3);

        messageBody.setMessageBodyHeader(messageType, serviceGroup, serviceCode);

        messageBody.toByteBuf(buffer);

        assertEquals(messageBody.getMessageBodyHeader(), buffer.readShort());

        buffer.release();
    }
}

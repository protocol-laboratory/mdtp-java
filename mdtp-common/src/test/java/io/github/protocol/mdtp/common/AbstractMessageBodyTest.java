package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.AbstractMessageBody;
import io.github.protocol.mdtp.common.model.MessageBodyHeader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AbstractMessageBodyTest {

    private AbstractMessageBody messageBody;

    @BeforeEach
    void setUp() {
        MessageBodyHeader messageBodyHeader = new MessageBodyHeader();
        messageBody = new AbstractMessageBody(messageBodyHeader){};
    }


    @Test
    void testGenerateRequestId() {
        short requestId1 = messageBody.generateId();
        short requestId2 = messageBody.generateId();
        assertEquals(Short.class, ((Object) requestId1).getClass());
        assertEquals(Short.class, ((Object) requestId2).getClass());
        assertNotEquals(requestId1, requestId2);
    }
}

package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.AbstractMessageBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AbstractMessageBodyTest {

    private AbstractMessageBody messageBody;

    @BeforeEach
    void setUp() {
        messageBody = new AbstractMessageBody(){};
    }


    @Test
    void testGenerateRequestId() {
        short requestId1 = messageBody.generateRequestId();
        short requestId2 = messageBody.generateRequestId();

        assertEquals(Short.class, ((Object) requestId1).getClass());
        assertEquals(Short.class, ((Object) requestId2).getClass());
        assertNotEquals(requestId1, requestId2);
    }
}

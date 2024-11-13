package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.MessageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTypeTest {
    @Test
    void testRequestCode() {
        assertEquals(0, MessageType.REQUEST.getCode());
    }

    @Test
    void testResponseCode() {
        assertEquals(1, MessageType.RESPONSE.getCode());
    }

    @Test
    void testNotifyCode() {
        assertEquals(2, MessageType.NOTIFY.getCode());
    }
}

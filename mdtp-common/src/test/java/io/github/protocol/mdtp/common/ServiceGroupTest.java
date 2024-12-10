package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.ServiceGroup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceGroupTest {
    @Test
    public void testFromCode_ValidCode() {
        ServiceGroup group = ServiceGroup.fromCode(1);
        assertEquals(ServiceGroup.DISCOVERY_SERVICE, group);

        group = ServiceGroup.fromCode(2);
        assertEquals(ServiceGroup.SECURITY_SERVICE, group);

        group = ServiceGroup.fromCode(3);
        assertEquals(ServiceGroup.CONNECTION_SERVICE, group);

        group = ServiceGroup.fromCode(4);
        assertEquals(ServiceGroup.BILLING_SERVICE, group);

        group = ServiceGroup.fromCode(5);
        assertEquals(ServiceGroup.SETTING_SERVICE, group);

        group = ServiceGroup.fromCode(6);
        assertEquals(ServiceGroup.METHOD_SERVICE, group);
    }

    @Test
    public void testFromCode_InvalidCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            ServiceGroup.fromCode(999);
        });
    }
}

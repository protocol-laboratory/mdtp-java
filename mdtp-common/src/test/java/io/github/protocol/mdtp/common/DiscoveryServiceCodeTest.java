package io.github.protocol.mdtp.common;

import io.github.protocol.mdtp.common.model.DiscoveryServiceCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiscoveryServiceCodeTest {

    @Test
    public void testFromCode_ValidCode() {
        DiscoveryServiceCode code = DiscoveryServiceCode.fromCode(1);
        assertEquals(DiscoveryServiceCode.DEVICE_DISCOVERY, code);

        code = DiscoveryServiceCode.fromCode(2);
        assertEquals(DiscoveryServiceCode.DEVICE_INFO_QUERY, code);
    }

    @Test
    public void testFromCode_InvalidCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            DiscoveryServiceCode.fromCode(999);
        });
    }
}

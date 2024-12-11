package io.github.protocol.mdtp.server;

import io.github.protocol.mdtp.common.model.Device;

public class MdtpServerConfig {
    public Device device;

    public MdtpServerConfig device(Device device) {
        this.device = device;
        return this;
    }
}

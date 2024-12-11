package io.github.protocol.mdtp.examples;

import io.github.protocol.mdtp.common.model.Address;
import io.github.protocol.mdtp.common.model.Device;
import io.github.protocol.mdtp.server.MdtpServer;
import io.github.protocol.mdtp.server.MdtpServerConfig;

import java.util.List;

public class MdtpServerExample {
    public static void main(String[] args) throws Exception {
        Device device = Device.builder()
                .mask((byte) 1)
                .deviceStatus((byte) 0x01)
                .addressCount((byte) 1)
                .addresses(List.of(new Address(Address.IPV4_TYPE, new byte[]{127, 0, 0, 1})))
                .port((short) 4146)
                .deviceType(1)
                .uniqueId(new byte[]{0x01, 0x02, 0x03, 0x04})
                .deviceName("discovered endpoint").build();
        MdtpServerConfig mdtpServerConfig  = new MdtpServerConfig().device(device);
        MdtpServer mdtpServer = new MdtpServer(mdtpServerConfig);
        mdtpServer.start();
    }
}

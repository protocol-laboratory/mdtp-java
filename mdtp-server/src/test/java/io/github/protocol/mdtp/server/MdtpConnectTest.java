package io.github.protocol.mdtp.server;

import io.github.protocol.mdtp.common.model.Address;
import io.github.protocol.mdtp.common.model.Device;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MdtpConnectTest {
    @Test
    public void mdtpConnect() throws Exception {
        Device device = Device.builder()
                .addresses(List.of(new Address(Address.IPV4_TYPE, new byte[]{127,0,0,1})))
                .port((short) 4146).build();
        MdtpServerConfig mdtpServerConfig  = new MdtpServerConfig().device(device);
        MdtpServer mdtpServer = new MdtpServer(mdtpServerConfig);
        mdtpServer.start();
        mdtpServer.close();
    }
}

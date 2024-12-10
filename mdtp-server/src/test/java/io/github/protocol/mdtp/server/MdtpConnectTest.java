package io.github.protocol.mdtp.server;

import org.junit.jupiter.api.Test;

public class MdtpConnectTest {
    @Test
    public void mdtpConnect() throws Exception {
        MdtpServerConfig mdtpServerConfig  = new MdtpServerConfig().host("localhost").port(0);
        MdtpServer mdtpServer = new MdtpServer(mdtpServerConfig);
        mdtpServer.start();
        mdtpServer.close();
    }
}

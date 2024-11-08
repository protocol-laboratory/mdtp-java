package io.github.protocol.mdtp.server;

import io.github.protocol.mdtp.client.MdtpClient;
import io.github.protocol.mdtp.client.MdtpClientConfig;
import org.junit.jupiter.api.Test;

public class MdtpConnectTest {
    @Test
    public void mdtpConnect() throws Exception {
        MdtpServerConfig mdtpServerConfig  = new MdtpServerConfig().host("localhost").port(0);
        MdtpServer mdtpServer = new MdtpServer(mdtpServerConfig);
        mdtpServer.start();
        MdtpClientConfig mdtpClientConfig = new MdtpClientConfig().host("localhost").port(mdtpServer.listenPort());
        MdtpClient mdtpClient = new MdtpClient(mdtpClientConfig);
        mdtpClient.start();
        mdtpClient.close();
        mdtpServer.close();
    }
}

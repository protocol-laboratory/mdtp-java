package io.github.protocol.mdtp.client;

import io.github.protocol.mdtp.server.MdtpServer;
import io.github.protocol.mdtp.server.MdtpServerConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class MdtpClientTest {

    private MdtpServer mdtpServer;
    private MdtpClient mdtpClient;


    @BeforeEach
    public void setUp() throws Exception {
        MdtpServerConfig mdtpServerConfig  = new MdtpServerConfig().host("localhost").port(4146);
        mdtpServer = new MdtpServer(mdtpServerConfig);
        mdtpServer.start();

        MdtpClientConfig config = new MdtpClientConfig().host("localhost").port(4146);
        mdtpClient = new MdtpClient(config);

    }

    @AfterEach
    public void close() throws IOException {
        mdtpServer.close();
    }

    @Test
    public void testStartConnection() throws Exception {
        mdtpClient.start();
    }

    @Test
    public void testSendDeviceDiscoveryRequest() throws Exception {
        mdtpClient.start();
        int[] deviceTypes = {1, 2, 3};
        mdtpClient.sendDeviceDiscoveryRequest(deviceTypes);
    }

    @Test
    public void testSendDeviceDiscoveryRequestWithNullDeviceTypes() throws Exception {
        mdtpClient.start();
        mdtpClient.sendDeviceDiscoveryRequest(null);
    }

}

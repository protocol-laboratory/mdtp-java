package io.github.protocol.mdtp.examples;

import io.github.protocol.mdtp.client.MdtpClient;
import io.github.protocol.mdtp.client.MdtpClientConfig;

public class MdtpClientExample {
    public static void main(String[] args) throws Exception {
        MdtpClientConfig mdtpClientConfig = new MdtpClientConfig().host("localhost").port(4146);
        MdtpClient mdtpClient = new MdtpClient(mdtpClientConfig);
        mdtpClient.start();
    }
}

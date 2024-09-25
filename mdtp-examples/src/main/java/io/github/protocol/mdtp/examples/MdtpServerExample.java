package io.github.protocol.mdtp.examples;

import io.github.protocol.mdtp.server.MdtpServer;
import io.github.protocol.mdtp.server.MdtpServerConfig;

public class MdtpServerExample {
    public static void main(String[] args) throws Exception {
        MdtpServerConfig mdtpServerConfig  = new MdtpServerConfig().host("localhost").port(4146);
        MdtpServer mdtpServer = new MdtpServer(mdtpServerConfig);
        mdtpServer.start();
    }
}

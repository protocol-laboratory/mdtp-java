package io.github.protocol.mdtp.server;

public class MdtpServerConfig {
    public String host;

    public int port;

    public MdtpServerConfig host(String host) {
        this.host = host;
        return this;
    }

    public MdtpServerConfig port(int port) {
        this.port = port;
        return this;
    }
}

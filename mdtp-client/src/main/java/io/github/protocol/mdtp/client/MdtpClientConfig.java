package io.github.protocol.mdtp.client;

public class MdtpClientConfig {
    public String host;

    public int port;

    public MdtpClientConfig host(String host) {
        this.host = host;
        return this;
    }

    public MdtpClientConfig port(int port) {
        this.port = port;
        return this;
    }
}

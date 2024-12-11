package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Address {
    public static final byte IPV4_TYPE = 4;
    public static final byte IPV6_TYPE = 6;

    private final byte type;
    private final byte[] value;

    public Address(byte type, byte[] value) {
        this.type = type;
        this.value = value;
    }

    public String getIpString() throws UnknownHostException {
        return InetAddress.getByAddress(value).getHostAddress();
    }

    public void writeByteBuf(ByteBuf buffer) {
        buffer.writeByte(type);
        buffer.writeBytes(value);
    }

    public static Address readByteBuf(ByteBuf buffer) {
        byte type = buffer.readByte();
        int length = (type == IPV4_TYPE) ? 4 : 16;
        byte[] value = new byte[length];
        buffer.readBytes(value);
        return new Address(type, value);
    }
}

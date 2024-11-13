package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class SecurityHeader {
    private byte[] encryptionData;

    public ByteBuf toByteBuf(ByteBuf buffer) {
        for (int data : encryptionData) {
            buffer.writeInt(data);
        }
        return buffer;
    }

}

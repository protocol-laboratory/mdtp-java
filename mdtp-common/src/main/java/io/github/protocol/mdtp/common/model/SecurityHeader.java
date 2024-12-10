package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class SecurityHeader {
    private byte[] encryptionData;

    public void writeByteBuf(ByteBuf buffer) {
        for (int data : encryptionData) {
            buffer.writeInt(data);
        }
    }

}

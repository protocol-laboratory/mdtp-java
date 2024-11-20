package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CDATHeader {

    private byte formatType;

    private byte protocolVersion;

    private short messageLength;

    private long timestamp;

    private byte flags;

    private Integer sequenceNumber;

    private Integer logicalChannelId;

    public ByteBuf toByteBuf(ByteBuf buffer) {
        buffer.writeByte(formatType);
        buffer.writeByte(protocolVersion);
        buffer.writeShort(messageLength);
        buffer.writeLong(timestamp);
        buffer.writeByte(flags);
        buffer.writeInt(sequenceNumber);
        buffer.writeInt(logicalChannelId);
        return buffer;
    }

    public static CDATHeader fromByteBuf(ByteBuf buffer) {
        CDATHeader header = new CDATHeader();

        header.setFormatType(buffer.readByte());
        header.setProtocolVersion(buffer.readByte());
        header.setMessageLength(buffer.readShort());
        header.setTimestamp(buffer.readLong());
        header.setFlags(buffer.readByte());
        header.setSequenceNumber(buffer.readInt());
        header.setLogicalChannelId(buffer.readInt());

        return header;
    }
}

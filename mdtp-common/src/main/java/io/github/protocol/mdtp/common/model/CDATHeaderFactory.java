package io.github.protocol.mdtp.common.model;

public class CDATHeaderFactory {
    public static CDATHeader createMessageTransferCDATHeader() {
        return initializeDefault(new CDATHeader(), (byte) 0x00);
    }

    public static CDATHeader createDeviceDiscoveryCDATHeader() {
        return initializeDefault(new CDATHeader(), (byte) 0x02);
    }

    private static CDATHeader initializeDefault(CDATHeader header, byte formatType) {
        header.setFormatType(formatType);
        header.setProtocolVersion((byte) 1);
        header.setMessageLength((short) 0);
        header.setTimestamp(System.currentTimeMillis());
        header.setFlags((byte) 0b01100000);

        if (formatType == 0x00) {
            header.setSequenceNumber(0);
            header.setLogicalChannelId(0);
        } else {
            header.setSequenceNumber(null);
            header.setLogicalChannelId(null);
        }

        return header;
    }
}

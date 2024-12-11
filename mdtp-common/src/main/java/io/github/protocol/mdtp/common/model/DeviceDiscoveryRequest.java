package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.Data;


@Data
public class DeviceDiscoveryRequest extends AbstractMessageBody {

    private short requestId;

    private byte mask;

    private byte deviceTypeCount;

    private int[] deviceTypes;

    public DeviceDiscoveryRequest() {
        super(MessageBodyHeader.DEVICE_DISCOVERY_REQUEST);
    }

    public void writeByteBuf(ByteBuf buffer) {
        super.writeByteBuf(buffer);
        buffer.writeShort(requestId);
        buffer.writeByte(mask);
        buffer.writeByte(deviceTypeCount);
        if (deviceTypeCount > 0) {
            for (int deviceType : deviceTypes) {
                buffer.writeInt(deviceType);
            }
        }
    }

    public static DeviceDiscoveryRequest fromByteBuf(ByteBuf data) {
        DeviceDiscoveryRequest request = new DeviceDiscoveryRequest();
        request.requestId = data.readShort();
        request.mask = data.readByte();
        request.deviceTypeCount = data.readByte();

        int length = request.deviceTypeCount;
        request.deviceTypes = new int[length];
        for (int i = 0; i < length; i++) {
            request.deviceTypes[i] = data.readInt();
        }
        return request;
    }
}

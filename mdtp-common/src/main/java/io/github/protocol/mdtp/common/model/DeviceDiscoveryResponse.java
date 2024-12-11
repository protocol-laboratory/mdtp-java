package io.github.protocol.mdtp.common.model;

import io.netty.buffer.ByteBuf;
import lombok.Data;


@Data
public class DeviceDiscoveryResponse extends AbstractMessageBody {
    private short requestId;

    private short responseId;

    private Device device;

    public DeviceDiscoveryResponse() {
        super(MessageBodyHeader.DEVICE_DISCOVERY_RESPONSE);
    }

    public void writeByteBuf(ByteBuf buffer) {
        super.writeByteBuf(buffer);
        buffer.writeShort(requestId);
        buffer.writeShort(responseId);
        device.writeByteBuf(buffer);
    }

    public static DeviceDiscoveryResponse readFromBuffer(ByteBuf buffer) {
        DeviceDiscoveryResponse response = new DeviceDiscoveryResponse();
        response.setRequestId(buffer.readShort());
        response.setResponseId(buffer.readShort());
        response.setDevice(Device.readByteBuf(buffer));
        return response;
    }
}

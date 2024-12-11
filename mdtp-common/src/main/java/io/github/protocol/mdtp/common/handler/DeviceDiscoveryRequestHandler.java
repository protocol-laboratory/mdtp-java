package io.github.protocol.mdtp.common.handler;

import io.github.protocol.mdtp.common.model.Attributes;
import io.github.protocol.mdtp.common.model.CDATHeader;
import io.github.protocol.mdtp.common.model.CDATHeaderFactory;
import io.github.protocol.mdtp.common.model.Device;
import io.github.protocol.mdtp.common.model.DeviceDiscoveryRequest;
import io.github.protocol.mdtp.common.model.DeviceDiscoveryResponse;
import io.github.protocol.mdtp.common.model.MdtpPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeviceDiscoveryRequestHandler implements MessageBodyHandler {

    @Override
    public void handle (ChannelHandlerContext ctx, MdtpPacket requestPacket) {
        log.info("start to send device discovery response.");
        DeviceDiscoveryRequest deviceDiscoveryRequest = (DeviceDiscoveryRequest) requestPacket.getBody();
        DeviceDiscoveryResponse deviceDiscoveryResponse = new DeviceDiscoveryResponse();
        deviceDiscoveryResponse.setRequestId(deviceDiscoveryRequest.getRequestId());
        deviceDiscoveryResponse.setResponseId(deviceDiscoveryResponse.generateId());
        Device device = ctx.channel().attr(Attributes.DEVICE_KEY).get();
        deviceDiscoveryResponse.setDevice(device);

        CDATHeader cdatHeader = CDATHeaderFactory.createDeviceDiscoveryCDATHeader();

        MdtpPacket packet = new MdtpPacket();
        packet.setHeader(cdatHeader);
        packet.setSecurityHeader(null);
        packet.setBody(deviceDiscoveryResponse);
        packet.setSignature(null);
        ctx.channel().writeAndFlush(packet.toByteBuf());
        log.info("send device discovery response success.");
    }
}

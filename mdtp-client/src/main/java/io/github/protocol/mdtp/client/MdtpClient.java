package io.github.protocol.mdtp.client;

import io.github.protocol.mdtp.common.codec.MdtpDecoder;
import io.github.protocol.mdtp.common.model.CDATHeader;
import io.github.protocol.mdtp.common.model.CDATHeaderFactory;
import io.github.protocol.mdtp.common.model.DeviceDiscoveryRequest;
import io.github.protocol.mdtp.common.model.MdtpPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

@Slf4j
public class MdtpClient implements Closeable {
    private final MdtpClientConfig config;

    private EventLoopGroup group;

    private ChannelFuture channelFuture;


    public MdtpClient(MdtpClientConfig config) {
        this.config = config;
    }

    public void start() throws Exception {
        if (group != null) {
            throw new IllegalAccessException("mdtp client already started");
        }
        log.info("mdtp client is starting, config is {}", this.config);
        this.group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.remoteAddress(this.config.host, this.config.port);
        bootstrap.group(this.group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new MdtpDecoder());
                    }
                });
        this.channelFuture = bootstrap.connect().sync();
        if (channelFuture.isSuccess()) {
            log.info("mdtp client started");
        } else {
            log.error("mdtp client start failed", channelFuture.cause());
            throw new Exception("mdtp client start failed", channelFuture.cause());
        }
    }

    @Override
    public void close() throws IOException {
        if (this.group == null) {
            log.info("mdtp client already closed");
            return;
        }
        this.group.shutdownGracefully();
        log.info("mdtp client closed");
    }

    public void sendDeviceDiscoveryRequest(int[] deviceTypes) {
        log.info("start to send device discovery request.");
        DeviceDiscoveryRequest request = new DeviceDiscoveryRequest();
        request.setRequestId(request.generateId());

        if (deviceTypes == null) {
            request.setDeviceTypeCount((byte) 0);
        }

        if (deviceTypes != null && deviceTypes.length > 0) {
            request.setMask((byte) 1);
            request.setDeviceTypeCount((byte) deviceTypes.length);
            request.setDeviceTypes(deviceTypes);
        }

        CDATHeader cdatHeader = CDATHeaderFactory.createDeviceDiscoveryCDATHeader();

        MdtpPacket packet = new MdtpPacket();
        packet.setHeader(cdatHeader);
        packet.setSecurityHeader(null);
        packet.setBody(request);
        packet.setSignature(null);

        this.channelFuture.channel().writeAndFlush(packet.toByteBuf());
        log.info("send device discovery request success.");
    }
}

package io.github.protocol.mdtp.server;

import io.github.protocol.mdtp.common.codec.MdtpDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;

@Slf4j
public class MdtpServer implements Closeable {
    private final MdtpServerConfig config;

    private EventLoopGroup acceptorGroup;

    private EventLoopGroup ioGroup;

    private Channel channel;

    public MdtpServer(MdtpServerConfig config) {
        this.config = config;
    }

    public void start() throws Exception {
        if (this.acceptorGroup != null) {
            throw new IllegalStateException("mdtp server already started");
        }
        log.info("mdtp server is starting, config is {}", this.config);
        this.acceptorGroup = new NioEventLoopGroup();
        this.ioGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(this.acceptorGroup, this.ioGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.localAddress(new InetSocketAddress(this.config.host, this.config.port));
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                log.info("init channel for remote address: {}", socketChannel.remoteAddress());
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new MdtpDecoder());
                pipeline.addLast(new MdtpServerHandler());
            }
        });
        ChannelFuture channelFuture = serverBootstrap.bind().sync();
        if (channelFuture.isSuccess()) {
            this.channel = channelFuture.channel();
            log.info("mdtp server started");
        } else {
            log.error("mdtp server start failed", channelFuture.cause());
            throw new Exception("mdtp server start failed", channelFuture.cause());
        }
    }

    public int listenPort() {
        if (this.channel != null && this.channel.localAddress() instanceof InetSocketAddress) {
            return ((InetSocketAddress) this.channel.localAddress()).getPort();
        }
        return -1;
    }

    @Override
    public void close() throws IOException {
        if (this.acceptorGroup != null) {
            this.acceptorGroup.shutdownGracefully();
        }
        if (this.ioGroup != null) {
            this.ioGroup.shutdownGracefully();
        }
    }
}

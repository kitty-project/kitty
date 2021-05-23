package com.julianjupiter.kitty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;

/**
 * @author Julian Jupiter
 */
class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    private final Configuration configuration;
    private final SslContext sslContext;

    HttpServerInitializer(Configuration configuration, SslContext sslContext) {
        this.configuration = configuration;
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        var channelPipeline = socketChannel.pipeline();
        if (this.sslContext != null) {
            channelPipeline.addLast(sslContext.newHandler(socketChannel.alloc()));
        }
        channelPipeline.addLast(new HttpRequestDecoder());
        channelPipeline.addLast(new HttpResponseEncoder());
        channelPipeline.addLast(new HttpServerHandler(configuration));
    }
}

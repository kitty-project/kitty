package com.julianjupiter.kitty;

import com.julianjupiter.kitty.util.LoggerFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;

/**
 * @author Julian Jupiter
 */
class NettyHttpServer implements HttpServer {
    private static final System.Logger logger = LoggerFactory.getLogger(NettyHttpServer.class);
    private final Configuration configuration;
    private static final boolean SSL = System.getProperty("ssl") != null;

    public NettyHttpServer(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void run() throws InterruptedException {
        SslContext sslContext = null;
        if (SSL) {
            SelfSignedCertificate selfSignedCertificate = null;
            try {
                selfSignedCertificate = new SelfSignedCertificate();
                sslContext = SslContextBuilder.forServer(selfSignedCertificate.certificate(), selfSignedCertificate.privateKey()).build();
            } catch (CertificateException | SSLException exception) {
                logger.log(System.Logger.Level.WARNING, () -> "Error in SSL, " + exception.getMessage());
            }
        }

        var bossEventLoopGroup = new NioEventLoopGroup(1);
        var workerEventLoopGroup = new NioEventLoopGroup();
        try {
            var serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossEventLoopGroup, workerEventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpServerInitializer(this.configuration, sslContext));
            var serverConfiguration = this.configuration.server();
            var hostAddress = serverConfiguration.hostAddress();
            var port = serverConfiguration.port();
            var channel = serverBootstrap
                    .localAddress(hostAddress, port)
                    .bind(port)
                    .sync()
                    .channel();
            logger.log(System.Logger.Level.INFO, "Server is running on " + (SSL ? "https" : "http") +
                    "://" + hostAddress + ":" + port + " with context path '" + serverConfiguration.contextPath() + "'");
            channel.closeFuture()
                    .sync();
        } finally {
            bossEventLoopGroup.shutdownGracefully();
            workerEventLoopGroup.shutdownGracefully();
        }
    }
}

package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.Response;
import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.util.LoggerFactory;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.cookie.CookieHeaderNames;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import io.netty.util.CharsetUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Julian Jupiter
 */
@ChannelHandler.Sharable
class HttpServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final System.Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);
    private final Configuration configuration;
    private final Map<String, List<Route>> routes;
    private HttpRequest nettyHttpRequest;
    private Response response;
    private final StringBuilder responseData = new StringBuilder();

    public HttpServerHandler(Configuration configuration, Map<String, List<Route>> routes) {
        this.configuration = configuration;
        this.routes = routes;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Object message) {
        final var requestFactory = RequestFactory.create();
        if (message instanceof HttpRequest httpRequest) {
            this.nettyHttpRequest = httpRequest;
            if (HttpUtil.is100ContinueExpected(httpRequest)) {
                writeResponse(context);
            }
        }

        if (message instanceof HttpContent httpContent) {
            if (message instanceof LastHttpContent trailer) {
                var kittyRequest = requestFactory.createRequest(this.nettyHttpRequest, httpContent);
                var route = this.route(kittyRequest);
                var handler = route.handler();
                var initialResponse = ResponseFactory.create()
                        .createResponse(kittyRequest);
                this.response = handler.handle(kittyRequest, initialResponse);
                writeResponse(context, trailer);
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }

    private void writeResponse(ChannelHandlerContext context) {
        var fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE, Unpooled.EMPTY_BUFFER);
        logger.log(System.Logger.Level.INFO, "XXX");
        context.write(fullHttpResponse);
    }

    private void writeResponse(ChannelHandlerContext context, LastHttpContent trailer) {
        this.responseData.setLength(0);
        this.responseData.append(this.response.body() != null && this.response.body().isReadable() ? this.response.body().toString() : "");

        boolean keepAlive = HttpUtil.isKeepAlive(this.nettyHttpRequest);

        FullHttpResponse httpResponse = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.valueOf(this.response.status().statusCode()),
                Unpooled.copiedBuffer(responseData.toString(), CharsetUtil.UTF_8)
        );

        Arrays.stream(this.response.headers())
                .forEach(header -> httpResponse.headers().set(header.name(), header.value()));

        if (keepAlive) {
            httpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, httpResponse.content().readableBytes());
            httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        Arrays.stream(this.response.cookies())
                .forEach(cookie -> {
                    var nettyCookie = new DefaultCookie(cookie.name(), cookie.value());
                    nettyCookie.setDomain(cookie.domain());
                    nettyCookie.setPath(cookie.path());
                    nettyCookie.setMaxAge(cookie.maxAge());
                    nettyCookie.setSecure(cookie.secure());
                    nettyCookie.setHttpOnly(cookie.httpOnly());
                    var kittySameSite = cookie.sameSite();
                    if (kittySameSite != null) {
                        nettyCookie.setSameSite(CookieHeaderNames.SameSite.valueOf(kittySameSite.toString()));
                    }
                    httpResponse.headers()
                            .add(HttpHeaderNames.SET_COOKIE, ServerCookieEncoder.STRICT.encode(nettyCookie));
                });

        if (!this.responseData.isEmpty()) {
            var contentTypeHeader = httpResponse.headers().get("Content-Type");
            if (contentTypeHeader == null || contentTypeHeader.isBlank()) {
                httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
            }
        }

        context.write(httpResponse);

        if (!keepAlive) {
            context.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

    private Route route(Request request) {
        List<Route> foundRoutes = this.routes.entrySet().stream()
                .filter(routeGroup -> {
                    var requestPath = request.target().getPath().substring(this.configuration.server().contextPath().length());
                    return PathMatcher.create(routeGroup.getKey())
                            .match(requestPath)
                            .matches();
                })
                .flatMap(routeGroup -> routeGroup.getValue().stream())
                .toList();

        if (foundRoutes.isEmpty()) {
            return new KittyNotFoundRoute();
        }

        return foundRoutes.stream()
                .filter(route -> route.method() == request.method())
                .findFirst()
                .orElse(new KittyHttpMethodNotAllowedRoute(foundRoutes.stream()
                        .map(Route::method)
                        .toList()
                        .toArray(new HttpMethod[0]))
                );
    }
}
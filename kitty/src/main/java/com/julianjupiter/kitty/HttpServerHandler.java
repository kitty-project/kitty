package com.julianjupiter.kitty;

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
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import io.netty.util.CharsetUtil;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Julian Jupiter
 */
@ChannelHandler.Sharable
public class HttpServerHandler extends SimpleChannelInboundHandler<Object> {
    private final Configuration configuration;
    private HttpRequest request;
    private final StringBuilder responseData = new StringBuilder();

    public HttpServerHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Object message) {
        if (message instanceof HttpRequest httpRequest) {
            this.request = httpRequest;
            if (HttpUtil.is100ContinueExpected(httpRequest)) {
                writeResponse(context);
            }

            responseData.setLength(0);
            responseData.append(formatGeneral(httpRequest));
            responseData.append(formatRequestHeaders(httpRequest));
            responseData.append(formatParams(httpRequest));
        }

        responseData.append(evaluateDecoderResult(this.request));

        if (message instanceof HttpContent httpContent) {
            responseData.append(formatBody(httpContent));
            responseData.append(evaluateDecoderResult(this.request));
            if (message instanceof LastHttpContent trailer) {
                responseData.append(prepareLastResponse(trailer));
                writeResponse(context, trailer, responseData);
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
        context.write(fullHttpResponse);
    }

    private void writeResponse(ChannelHandlerContext context, LastHttpContent trailer, StringBuilder responseData) {
        boolean keepAlive = HttpUtil.isKeepAlive(this.request);

        FullHttpResponse httpResponse = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                trailer.decoderResult().isSuccess() ? HttpResponseStatus.OK : HttpResponseStatus.BAD_REQUEST,
                Unpooled.copiedBuffer(responseData.toString(), CharsetUtil.UTF_8)
        );

        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");

        if (keepAlive) {
            httpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, httpResponse.content().readableBytes());
            httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        // Write Cookie
        // Encode the cookie.
        var cookieString = this.request.headers().get(HttpHeaderNames.COOKIE);
        if (cookieString != null) {
            Set<Cookie> cookies = ServerCookieDecoder.STRICT.decode(cookieString);
            if (!cookies.isEmpty()) {
                // Reset the cookies if necessary.
                for (Cookie cookie : cookies) {
                    httpResponse.headers()
                            .add(HttpHeaderNames.SET_COOKIE, ServerCookieEncoder.STRICT.encode(cookie));
                }
            }
        } else {
            // Browser sent no cookie.  Add some.
            httpResponse.headers()
                    .add(HttpHeaderNames.SET_COOKIE, ServerCookieEncoder.STRICT.encode("created_at", OffsetDateTime.now().toString()));
            httpResponse.headers()
                    .add(HttpHeaderNames.SET_COOKIE, ServerCookieEncoder.STRICT.encode("http_server", "Netty"));
            httpResponse.headers()
                    .add(HttpHeaderNames.SET_COOKIE, ServerCookieEncoder.STRICT.encode("web_framework", "Kitty"));
        }

        context.write(httpResponse);

        if (!keepAlive) {
            context.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

    private static StringBuilder formatGeneral(HttpRequest request) {
        var responseData = new StringBuilder();
        responseData.append("GENERAL")
                .append("\r\n");
        responseData.append("Version: ")
                .append(request.protocolVersion())
                .append("\r\n");
        responseData.append("Request URI: ")
                .append(request.uri())
                .append("\r\n");
        responseData.append("Request Method: ")
                .append(request.method())
                .append("\r\n");
        responseData.append("Remote Address: ")
                .append(request.headers().get(HttpHeaderNames.HOST, "unknown"))
                .append("\r\n");
        responseData.append("Referrer Policy: ")
                .append(request.headers().get(HttpHeaderNames.REFERER, "strict-origin-when-cross-origin"))
                .append("\r\n\r\n");

        return responseData;
    }

    private static StringBuilder formatRequestHeaders(HttpRequest request) {
        var responseData = new StringBuilder();
        var headers = request.headers();
        responseData.append("REQUEST HEADERS")
                .append("\r\n");
        if (!headers.isEmpty()) {
            for (Map.Entry<String, String> header : headers) {
                var key = header.getKey();
                var value = header.getValue();
                responseData.append(key)
                        .append(": ")
                        .append(value)
                        .append("\r\n");
            }

            responseData.append("\r\n");
        }

        return responseData;
    }

    private static StringBuilder formatParams(HttpRequest request) {
        var responseData = new StringBuilder();
        var queryStringDecoder = new QueryStringDecoder(request.uri());
        responseData.append("PARAMS")
                .append("\r\n");
        Map<String, List<String>> params = queryStringDecoder.parameters();
        if (!params.isEmpty()) {
            for (Map.Entry<String, List<String>> param : params.entrySet()) {
                String key = param.getKey();
                List<String> values = param.getValue();
                for (var value : values) {
                    responseData.append(key)
                            .append(": ")
                            .append(value)
                            .append("\r\n");
                }
            }

            responseData.append("\r\n");
        }

        return responseData;
    }

    private static StringBuilder formatBody(HttpContent httpContent) {
        var responseData = new StringBuilder();
        responseData.append("BODY")
                .append("\r\n");
        var content = httpContent.content();
        if (content.isReadable()) {
            responseData.append(content.toString(CharsetUtil.UTF_8));
            responseData.append("\r\n");
        }

        return responseData;
    }

    private static StringBuilder evaluateDecoderResult(HttpObject httpObject) {
        var responseData = new StringBuilder();
        var result = httpObject.decoderResult();

        if (!result.isSuccess()) {
            responseData.append("DECODER FAILURE")
                    .append("\r\n");
            responseData.append(result.cause());
            responseData.append("\r\n");
        }

        return responseData;
    }

    private static StringBuilder prepareLastResponse(LastHttpContent trailer) {
        var responseData = new StringBuilder();

        if (!trailer.trailingHeaders().isEmpty()) {
            responseData.append("\r\n");
            responseData.append("TRAILING HEADER")
                    .append("\r\n");
            for (var name : trailer.trailingHeaders().names()) {
                for (var value : trailer.trailingHeaders().getAll(name)) {
                    responseData.append(name)
                            .append(": ")
                            .append(value)
                            .append("\r\n");
                }
            }

            responseData.append("\r\n");
        }

        return responseData;
    }
}
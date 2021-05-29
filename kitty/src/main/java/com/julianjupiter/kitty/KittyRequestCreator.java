package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Body;
import com.julianjupiter.kitty.http.message.QueryParam;
import com.julianjupiter.kitty.http.message.Request;
import com.julianjupiter.kitty.http.message.RequestLine;
import com.julianjupiter.kitty.http.message.util.CookieSameSite;
import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.message.util.HttpVersion;
import com.julianjupiter.kitty.http.message.util.RequestHeaderName;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;

import java.net.URI;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Julian Jupiter
 */
final class KittyRequestCreator implements RequestCreator {
    private HttpRequest httpRequest;
    private HttpContent httpContent;

    @Override
    public RequestCreator nettyHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        return this;
    }

    @Override
    public RequestCreator nettyHttpContent(HttpContent httpContent) {
        this.httpContent = httpContent;
        return this;
    }

    @Override
    public Request createKittyRequest() {
        return new KittyRequest(this.createRequestLine(), this.createQueryParams(), this.createKittyHttpHeaders(), this.createKittyHttpCookies(), this.createBody());
    }

    private RequestLine createRequestLine() {
        var method = this.httpRequest.method().toString();
        var uri = this.httpRequest.uri();

        return new KittyRequestLine(HttpMethod.of(method), URI.create(uri), this.createHttpVersion());
    }

    private QueryParam[] createQueryParams() {
        var queryStringDecoder = new QueryStringDecoder(this.httpRequest.uri());

        return queryStringDecoder.parameters().entrySet().stream()
                .map(param -> {
                    var key = param.getKey();
                    var values = param.getValue().toArray(String[]::new);
                    return new KittyQueryParam(key, values);
                })
                .toArray(QueryParam[]::new);
    }

    private HttpVersion createHttpVersion() {
        return HttpVersion.fromString(this.httpRequest.protocolVersion().toString());
    }

    private KittyHttpHeaders createKittyHttpHeaders() {
        var kittyHttpHeaders = KittyHttpHeaders.create();
        this.httpRequest.headers().entries().stream()
                .filter(Predicate.not(httpHeader -> httpHeader.getKey().equalsIgnoreCase(RequestHeaderName.COOKIE.toString())))
                .map(httpHeader -> List.of(new KittyHeader(httpHeader.getKey(), httpHeader.getValue())))
                .flatMap(List::stream)
                .forEach(kittyHttpHeaders::add);

        return kittyHttpHeaders;
    }

    private KittyHttpCookies createKittyHttpCookies() {
        var kittyHttpCookies = KittyHttpCookies.create();
        this.httpRequest.headers().entries().stream()
                .filter(httpHeader -> httpHeader.getKey().equalsIgnoreCase(RequestHeaderName.COOKIE.toString()))
                .map(httpHeader -> {
                    var cookies = ServerCookieDecoder.STRICT.decode(httpHeader.getValue());
                    return cookies.stream()
                            .map(cookie -> {
                                var cookieBuilder = CookieBuilder.builder(cookie.name(), cookie.value())
                                        .domain(cookie.domain())
                                        .path(cookie.path())
                                        .maxAge(cookie.maxAge())
                                        .secure(cookie.isSecure())
                                        .httpOnly(cookie.isHttpOnly());
                                if (cookie instanceof DefaultCookie defaultCookie) {
                                    var nettyNameSite = defaultCookie.sameSite();
                                    if (nettyNameSite != null) {
                                        return cookieBuilder.sameSite(CookieSameSite.of(nettyNameSite.name()))
                                            .build();
                                    }
                                }
                                return cookieBuilder.build();
                            })
                            .toList();
                })
                .flatMap(List::stream)
                .forEach(kittyHttpCookies::add);
        return kittyHttpCookies;
    }

    private Body createBody() {
        var content = this.httpContent.content();

        return new KittyBody(content);
    }
}

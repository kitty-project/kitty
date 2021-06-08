package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Body;
import com.julianjupiter.kitty.http.message.QueryParam;
import com.julianjupiter.kitty.http.message.Request;
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
final class KittyRequestFactory implements RequestFactory {
    @Override
    public Request createRequest(HttpRequest httpRequest, HttpContent httpContent) {
        return new KittyRequest(
                this.createRequestLine(httpRequest),
                this.createQueryParams(httpRequest),
                this.createKittyHttpHeaders(httpRequest),
                this.createKittyHttpCookies(httpRequest),
                this.createBody(httpContent)
        );
    }

    private RequestLine createRequestLine(HttpRequest httpRequest) {
        var method = httpRequest.method().toString();
        var uri = httpRequest.uri();

        return new KittyRequestLine(HttpMethod.of(method), URI.create(uri), this.createHttpVersion(httpRequest));
    }

    private QueryParam[] createQueryParams(HttpRequest httpRequest) {
        var queryStringDecoder = new QueryStringDecoder(httpRequest.uri());

        return queryStringDecoder.parameters().entrySet().stream()
                .map(param -> {
                    var key = param.getKey();
                    var values = param.getValue().toArray(String[]::new);
                    return new KittyQueryParam(key, values);
                })
                .toArray(QueryParam[]::new);
    }

    private HttpVersion createHttpVersion(HttpRequest httpRequest) {
        return HttpVersion.fromString(httpRequest.protocolVersion().toString());
    }

    private KittyHttpHeaders createKittyHttpHeaders(HttpRequest httpRequest) {
        var headers = httpRequest.headers().entries().stream()
                .filter(Predicate.not(httpHeader -> httpHeader.getKey().equalsIgnoreCase(RequestHeaderName.COOKIE.toString())))
                .map(httpHeader -> new KittyHeader(httpHeader.getKey(), httpHeader.getValue()))
                .toList()
                .toArray(new KittyHeader[0]);

        return KittyHttpHeaders.create(headers);
    }

    private KittyHttpCookies createKittyHttpCookies(HttpRequest httpRequest) {
        var kittyHttpCookies = KittyHttpCookies.create();
        httpRequest.headers().entries().stream()
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

    private Body createBody(HttpContent httpContent) {
        var content = httpContent.content();

        return new KittyBody(content);
    }
}

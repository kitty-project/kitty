package com.julianjupiter.kitty.http.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Julian Jupiter
 */
public final class HttpHeaders {
    private final Map<String, List<HttpHeader>> headerMap = new HashMap<>();

    private HttpHeaders() {
    }

    public static HttpHeaders create() {
        return new HttpHeaders();
    }

    public HttpHeaders add(String name, String value) {
        var httpHeader = new HttpHeader(name, value);
        headerMap.compute(name, (n, httpHeaders) -> {
            if (httpHeaders == null) {
                httpHeaders = new ArrayList<>();
            }
            httpHeaders.add(httpHeader);
            return httpHeaders;
        });

        return this;
    }

    public HttpHeaders add(HttpHeader header) {
        headerMap.compute(header.name(), (n, httpHeaders) -> {
            if (httpHeaders == null) {
                httpHeaders = new ArrayList<>();
            }
            httpHeaders.add(header);
            return httpHeaders;
        });

        return this;
    }

    public HttpHeaders replace(String name, String value) {
        headerMap.compute(name, (n, v) -> {
            var headers = new ArrayList<HttpHeader>();
            headers.add(new HttpHeader(name, value));
            return headers;
        });
        return this;
    }

    public HttpHeaders replace(HttpHeader header) {
        headerMap.compute(header.name(), (name, value) -> {
            var headers = new ArrayList<HttpHeader>();
            headers.add(header);
            return headers;
        });
        return this;
    }

    public HttpHeaders remove(String name) {
        this.headerMap.remove(name);
        return this;
    }

    public HttpHeaders remove(HttpHeader header) {
        if (header == null) {
            this.headerMap.remove(null);
        } else {
            this.headerMap.remove(header.name());
        }
        return this;
    }

    public List<HttpHeader> get(String name) {
        return this.headerMap.getOrDefault(name, List.of());
    }
}

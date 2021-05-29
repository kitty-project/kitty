package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Julian Jupiter
 */
public final class HttpHeaders {
    private final Map<String, List<Header>> headerMap = new HashMap<>();

    private HttpHeaders() {
    }

    public static HttpHeaders create() {
        return new HttpHeaders();
    }

    public HttpHeaders add(String name, String value) {

        headerMap.compute(name, (n, httpHeaders) -> {
            if (httpHeaders == null) {
                httpHeaders = new ArrayList<>();
            }

            var headerOptional = httpHeaders.stream()
                    .filter(httpHeader -> httpHeader.value().equals(value))
                    .findFirst();
            if (headerOptional.isEmpty()) {
                var httpHeader = new Header(name, value);
                httpHeaders.add(httpHeader);
            }

            return httpHeaders;
        });

        return this;
    }

    public HttpHeaders add(Header header) {
        headerMap.compute(header.name(), (n, httpHeaders) -> {
            if (httpHeaders == null) {
                httpHeaders = new ArrayList<>();
            }

            var headerOptional = httpHeaders.stream()
                    .filter(httpHeader -> httpHeader.value().equals(header.value()))
                    .findFirst();
            if (headerOptional.isEmpty()) {
                httpHeaders.add(header);
            }

            return httpHeaders;
        });

        return this;
    }

    public HttpHeaders replace(String name, String value) {
        headerMap.compute(name, (n, v) -> {
            var headers = new ArrayList<Header>();
            headers.add(new Header(name, value));
            return headers;
        });
        return this;
    }

    public HttpHeaders replace(Header header) {
        headerMap.compute(header.name(), (name, value) -> {
            var headers = new ArrayList<Header>();
            headers.add(header);
            return headers;
        });
        return this;
    }

    public HttpHeaders remove(String name) {
        this.headerMap.remove(name);
        return this;
    }

    public HttpHeaders remove(Header header) {
        if (header == null) {
            this.headerMap.remove(null);
        } else {
            this.headerMap.remove(header.name());
        }
        return this;
    }

    public Map<String, List<Header>> get() {
        return this.headerMap;
    }

    public List<Header> get(String name) {
        return this.headerMap.getOrDefault(name, List.of());
    }
}

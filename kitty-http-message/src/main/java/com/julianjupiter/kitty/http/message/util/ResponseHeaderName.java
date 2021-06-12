package com.julianjupiter.kitty.http.message.util;

/**
 * @author Julian Jupiter
 */
public enum ResponseHeaderName {
    // General Headers
    CACHE_CONTROL("Cache-Control"),
    CONNECTION("Connection"),
    DATE("Date"),
    PRAGMA("Pragma"),
    TRAILER("Trailer"),
    TRANSFER_ENCODING("Transfer-Encoding"),
    UPGRADE("Upgrade"),
    VIA("Via"),
    WARNING("Warning"),
    // Response Headers
    A_IM("A-IM"),
    ACCEPT("Accept"),
    ACCEPT_CHARSET("Accept-Charset"),
    ACCEPT_DATETIME("Accept-Datetime"),
    ACCEPT_ENCODING("Accept-Encoding"),
    ACCEPT_LANGUAGE("Accept-Language"),
    ACCEPT_CONTROL_REQUEST_METHOD("Accept-Control-Request-Method"),
    ACCESS_CONTROL_REQUEST_HEADERS("Access-Control-Request-Headers"),
    ALLOW("Allow"),
    AUTHORIZATION("Authorization"),
    CONTENT_LENGTH("Content-Length"),
    EXPECT("Expect"),
    FORWARDED("Forwarded"),
    FROM("From"),
    HOST("Host"),
    HTTP2_SETTINGS("HTTP2-Settings"),
    IF_MATCH("If-Match"),
    IF_MODIFIED_SINCE("If-Modified-Since"),
    MAX_FORWARDS("Max-Forwards"),
    ORIGIN("Origin"),
    PROXY_AUTHORIZATION("Proxy-Authorization"),
    RANGE("Range"),
    REFERER("Referer"),
    SET_COOKIE("Set-Cookie"),
    TE("TE"),
    USER_AGENT("User-Agent"),
    UPGRADE_INSECURE_REQUESTS("Upgrade-Insecure-Requests"),
    X_REQUESTED_WITH("X-Requested-With"),
    DNT("DNT"),
    X_FORWARDED_FOR("X-Forwarded-For"),
    X_FORWARDED_HOST("X-Forwarded-Host"),
    X_FORWARDED_PROTO("X-Forwarded-Proto"),
    FRONT_END_HTTPS("Front-End-Https"),
    X_HTTP_METHOD_OVERRIDE("X-Http-Method-Override"),
    X_ATT_DEVICEID("X-ATT-DeviceId"),
    X_WAP_PROFILE("X-Wap-Profile"),
    PROXY_CONNECTION("Proxy-Connection"),
    X_UIDH("X-UIDH"),
    X_CSRF_TOKEN("X-Csrf-Token"),
    X_REQUEST_ID("X-Request-ID"),
    X_CORRELATION_ID("X-Correlation-ID"),
    // Entity headers
    CONTENT_MD5("Content-MD5"),
    CONTENT_TYPE("Content-Type");

    private final String value;

    ResponseHeaderName(String name) {
        this.value = name;
    }

    @Override
    public String toString() {
        return value;
    }
}

package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.Body;
import com.julianjupiter.kitty.http.message.Cookie;
import com.julianjupiter.kitty.http.message.Header;
import com.julianjupiter.kitty.http.message.Response;
import com.julianjupiter.kitty.http.message.util.HttpStatus;
import com.julianjupiter.kitty.util.LoggerFactory;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Julian Jupiter
 */
final class KittyResponse extends KittyMessage implements Response {
    private static final System.Logger logger = LoggerFactory.getLogger(KittyResponse.class);
    private HttpStatus status;

    KittyResponse(StatusLine statusLine) {
        super(statusLine.version());
        this.status = statusLine.status();
    }

    @Override
    public HttpStatus status() {
        return this.status;
    }

    @Override
    public Response status(int statusCode) {
        this.status = HttpStatus.of(statusCode);
        return this;
    }

    @Override
    public Response status(HttpStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public Response header(String name, String value) {
        this.kittyHttpHeaders.add(name, value);
        return this;
    }

    @Override
    public Response header(Header header) {
        this.kittyHttpHeaders.add(header);
        return this;
    }

    @Override
    public Response headers(Header[] headers) {
        this.kittyHttpHeaders.add(headers);
        return this;
    }

    @Override
    public Response cookie(String name, String value) {
        this.kittyHttpCookies.add(name, value);
        return this;
    }

    @Override
    public Response cookie(Cookie cookie) {
        this.kittyHttpCookies.add(cookie);
        return this;
    }

    @Override
    public Response cookies(Cookie[] cookies) {
        this.kittyHttpCookies.add(cookies);
        return this;
    }

    @Override
    public Response body(Object body) {
        this.body = this.createBody(body);
        return this;
    }

    private Body createBody(Object body) {
        try (var bos = new ByteArrayOutputStream();
             var oos = new ObjectOutputStream(bos)) {
            oos.writeObject(body);
            oos.flush();
            var data = bos.toByteArray();
            return new KittyBody(Unpooled.wrappedBuffer(data));
        } catch (IOException exception) {
            exception.printStackTrace();
            logger.log(System.Logger.Level.ERROR, "Error in creating body, " + exception.getMessage());
        }

        return null;
    }
}

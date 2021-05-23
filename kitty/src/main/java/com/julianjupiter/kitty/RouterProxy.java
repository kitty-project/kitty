package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.HttpMethod;
import com.julianjupiter.kitty.http.server.ContextHandler;
import com.julianjupiter.kitty.http.server.RequestHandler;

import java.util.Set;

/**
 * @author Julian Jupiter
 */
public interface RouterProxy<T> {
    T any(String path, RequestHandler handler);

    T any(String path, ContextHandler handler);

    T anyOf(Set<HttpMethod> methods, String path, RequestHandler handler);

    T anyOf(Set<HttpMethod> methods, String path, ContextHandler handler);

    T delete(String path, RequestHandler handler);

    T delete(String path, ContextHandler handler);

    T get(String path, RequestHandler handler);

    T get(String path, ContextHandler handler);

    T head(String path, RequestHandler handler);

    T head(String path, ContextHandler handler);

    T options(String path, RequestHandler handler);

    T options(String path, ContextHandler handler);

    T patch(String path, RequestHandler handler);

    T patch(String path, ContextHandler handler);

    T post(String path, RequestHandler handler);

    T post(String path, ContextHandler handler);

    T put(String path, RequestHandler handler);

    T put(String path, ContextHandler handler);

    T trace(String path, RequestHandler handler);

    T trace(String path, ContextHandler handler);
}

package com.julianjupiter.kitty;

import com.julianjupiter.kitty.http.message.util.HttpMethod;
import com.julianjupiter.kitty.http.server.ContextHandler;
import com.julianjupiter.kitty.http.server.RequestHandler;

import java.util.Set;

/**
 * @author Julian Jupiter
 */
interface RouteGroupProxy<T> extends RouterProxy<T> {
    T any(RequestHandler handler);

    T any(ContextHandler handler);

    T anyOf(Set<HttpMethod> methods, RequestHandler handler);

    T anyOf(Set<HttpMethod> methods, ContextHandler handler);

    T delete(RequestHandler handler);

    T delete(ContextHandler handler);

    T get(RequestHandler handler);

    T get(ContextHandler handler);

    T head(RequestHandler handler);

    T head(ContextHandler handler);

    T options(RequestHandler handler);

    T options(ContextHandler handler);

    T patch(RequestHandler handler);

    T patch(ContextHandler handler);

    T post(RequestHandler handler);

    T post(ContextHandler handler);

    T put(RequestHandler handler);

    T put(ContextHandler handler);

    T trace(RequestHandler handler);

    T trace(ContextHandler handler);
}

package com.julianjupiter.kitty;

import java.util.Map;
import java.util.Objects;

public class RequestImpl implements Request {
    private final Configuration configuration;
    private final Map<String, QueryParam> queryParams;

    RequestImpl(Configuration configuration, Map<String, QueryParam> queryParams) {
        this.configuration = configuration;
        this.queryParams = queryParams;
    }

    @Override
    public PathParam pathParam(String param) {
        return null;
    }

    @Override
    public QueryParam queryParam(String param) {
        Objects.requireNonNull(param, "Parameter 'param' cannot be null!");
        return queryParams.get(param);
    }
}

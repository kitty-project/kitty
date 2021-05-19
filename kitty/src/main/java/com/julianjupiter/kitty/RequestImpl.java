package com.julianjupiter.kitty;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class RequestImpl implements Request {
    private final Configuration configuration;
    private final Map<String, QueryParam> queryParams;

    RequestImpl(Configuration configuration, Map<String, QueryParam> queryParams) {
        this.configuration = configuration;
        this.queryParams = queryParams;
    }

    @Override
    public Optional<PathParam> pathParam(String param) {
        Objects.requireNonNull(param, "Parameter 'param' cannot be null!");

        return Optional.empty();
    }

    @Override
    public Optional<QueryParam> queryParam(String param) {
        Objects.requireNonNull(param, "Parameter 'param' cannot be null!");

        return Optional.ofNullable(queryParams.get(param));
    }
}

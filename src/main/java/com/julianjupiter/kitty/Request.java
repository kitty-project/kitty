package com.julianjupiter.kitty;

import java.util.Optional;

public interface Request {
    Optional<PathParam> pathParam(String param);

    Optional<QueryParam> queryParam(String param);
}

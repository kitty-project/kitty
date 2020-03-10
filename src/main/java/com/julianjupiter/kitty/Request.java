package com.julianjupiter.kitty;

public interface Request {
    PathParam pathParam(String param);

    QueryParam queryParam(String param);
}

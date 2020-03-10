package com.julianjupiter.kitty;

import org.glassfish.grizzly.http.io.NIOWriter;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import javax.json.bind.Jsonb;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

final class GrizzlyHttpHandler extends HttpHandler {
    private final Configuration configuration;
    private final Set<Route> routes;
    private NIOWriter out;

    GrizzlyHttpHandler(Configuration configuration, Set<Route> routes) {
        this.configuration = configuration;
        this.routes = routes;
    }

    @Override
    public void service(final Request request, final Response response) throws Exception {
        this.out = response.getNIOWriter();
        Route route = this.route(request.getMethod().getMethodString(), request.getRequestURI());
        this.handle(request, response, route);
    }

    private void handle(Request request, Response response, Route route) throws IOException {
        com.julianjupiter.kitty.Request req = new RequestImpl();
        com.julianjupiter.kitty.Response res = new ResponseImpl(this.configuration);
        RequestHandler handler = route.handler();
        com.julianjupiter.kitty.Response response1 = handler.handle(req, res);
        response.setStatus(response1.status().statusCode());
        response.setContentType(response1.contentType().toString());
        this.writeData(response1);
    }

    private Route route(String requestHttpMethod, String requestPath) {
        System.out.println(requestHttpMethod + ":" + requestPath);

        List<Route> foundRoutes = routes.stream()
                .filter(route -> PathPattern.compile(route.path())
                        .match(requestPath)
                        .matches()
                )
                .collect(Collectors.toUnmodifiableList());

        if (foundRoutes.isEmpty()) {
            return new RouteImpl(null, null, (req, res) -> res
                    .status(HttpStatus.NOT_FOUND)
                    .body("Error HTTP Status 404, page not found!")
            );
        }

        return foundRoutes.stream()
                .filter(route -> route.method().name().equals(requestHttpMethod))
                .findFirst()
                .orElse(new RouteImpl(null, null, (req, res) -> res
                        .status(HttpStatus.METHOD_NOW_ALLOWED)
                        .body("Error HTTP Status 405, HTTP method " + requestHttpMethod + " is not supported by " + requestPath + "!")
                ));
    }

    private void writeData(com.julianjupiter.kitty.Response response) throws IOException {
        try {
            Object body = response.body();
            ContentType contentType = response.contentType();
            if (contentType != null && contentType == ContentType.APPLICATION_JSON && !(body instanceof String)) {
                Object jsonMapper = this.configuration.jsonMapper();
                if (jsonMapper != null) {
                    if (jsonMapper instanceof Jsonb) {
                        Jsonb jsonb = (Jsonb) jsonMapper;
                        String json = jsonb.toJson(body);
                        this.out.write(json);
                    }
                } else {
                    throw new NullPointerException("JSON Mapper is null!");
                }
            } else {
                this.out.write(body.toString());
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
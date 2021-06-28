# Kitty Microframework

A very lightweight Java microframework

## Install Kitty

```shell
$ git clone https://github.com/kitty-project/kitty
$ cd kitty
$ git checkout develop
$ ./mvnw install
```

## Add Dependency

```shell
<dependency>
    <groupId>com.julianjupiter.kitty</groupId>
    <artifactId>kitty</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Sample Application

```java
import com.julianjupiter.kitty.Configuration;
import com.julianjupiter.kitty.Kitty;
import com.julianjupiter.kitty.ServerConfiguration;
import com.julianjupiter.kitty.http.message.util.HttpStatus;

public class App {
    private static final System.Logger logger = System.getLogger(App.class.getName());

    public static void main(String[] args) throws InterruptedException {
        var configuration = Configuration.builder()
                .server(ServerConfiguration.builder()
                        .port(7000)
                        .contextPath("/api")
                        .build())
                .build();

        var app = Kitty.meow(configuration);

        // GET http://localhost:7000/api/greetings
        app.get("/greetings", ctx -> {
            // Retrieve query param from http://localhost:7000/api/greetings?name=<value>
            var name = ctx.request().queryParam("name")
                    .map(QueryParam::value)
                    .orElse("Joe");
            return ctx.response()
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(String.format(
                            """
                            {"message": "Hello, %s!"}
                            """, name)
                    );
        });

        // GET http://localhost:7000/api/users/{id}
        app.get("/users/{id}", (request, response) -> {
            // Retrieve path param
            var id = request.pathParam("id")
                    .map(PathParam::asInt)
                    .orElse(0);
            return response
                    .status(200)
                    .header("Content-Type", "text/plain")
                    .body("User ID " + id);
        });

        // Group routes:
        // GET     http://localhost:7000/api/orders
        // POST    http://localhost:7000/api/orders
        // GET     http://localhost:7000/api/orders/{orderId}
        // PUT     http://localhost:7000/api/orders/{orderId}                
        // DELETE  http://localhost:7000/api/orders/{orderId}
        // GET     http://localhost:7000/api/orders/{orderId}/items
        // POST    http://localhost:7000/api/orders/{orderId}/items
        // POST    http://localhost:7000/api/orders/{orderId}/items/{itemId}
        // PUT     http://localhost:7000/api/orders/{orderId}/items/{itemId}
        // DELETE  http://localhost:7000/api/orders/{orderId}/items/{itemId}
        app.group("/orders", routeGroupBuilder -> routeGroupBuilder
                .get(Context::response)
                .post(Context::response)
                .group("/{id}", routeGroupBuilder1 -> routeGroupBuilder1
                        .get((request, response) -> response)
                        .put((request, response) -> response)
                        .delete((request, response) -> response)
                        .group("/items", routeGroupBuilder2 -> routeGroupBuilder2
                                .get((request, response) -> response)
                                .post((request, response) -> response)
                                .group("/{id}", routeGroupBuilder3 -> routeGroupBuilder3
                                        .get((request, response) -> response)
                                        .put((request, response) -> response)
                                        .delete((request, response) -> response)
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .build()
        );

        app.run(() -> logger.log(System.Logger.Level.INFO, "App is running..."));
    }
}
```
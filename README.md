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

        // POST http://localhost:7000/api/greetings
        app.get("/greetings", ctx -> ctx.response()
                .header("Content-Type", "application/json; charset=UTF-8")
                .body("{\"message\": \"Hello, world!\"}")
        );

        // GET http://localhost:7000/api/greetings
        // Request body: {"message", "Hello, world!"}
        app.post("/greetings", (request, response) -> {
            var body = request.body();
            logger.log(System.Logger.Level.INFO, body);
            return response
                    .status(HttpStatus.CREATED)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(body.toString());
        });

        app.run(() -> logger.log(System.Logger.Level.INFO, "App is running..."));
    }
}
```
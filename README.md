# Kitty Microframework
## A very lightweight Java microframework
It uses [Eclipse Grizzly](https://projects.eclipse.org/projects/ee4j.grizzly) HTTP framework as its underlying HTTP server functionality.
> The Eclipse Grizzly NIO framework has been designed to help developers to take advantage of the Java™ NIO API.
## Install Kitty
```shell
$ git clone https://github.com/kitty-project/kitty
$ cd kitty
$ git checkout develop
$ ./mvnw install
```
## Add as dependency
```xml
<dependency>
    <groupId>com.julianjupiter.kitty</groupId>
    <artifactId>kitty</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
## Sample Application
```java
import com.julianjupiter.kitty.Configuration;
import com.julianjupiter.kitty.HttpMethod;
import com.julianjupiter.kitty.Kitty;

import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        Configuration configuration = Configuration.builder()
                .contextPath("/api")
                .build();

        Kitty.create(configuration, router -> router
                .get("/users/{id:[0-9]+}", (request, response) -> response.body("Hi user 1!"))
                .anyOf(Set.of(HttpMethod.GET, HttpMethod.POST), "/greetings", (request, response) -> response.body(Map.of("name", "Kitty")).render("greetings")).withoutContextPath()
                .get("/hi", (request, response) -> response.body("Hi"))
                .any("/about", (request, response) -> response.render("about")).withoutContextPath()
        ).run(7001, () -> System.out.println("App is listening on port " + 7001 + "..."));

    }
}
```
Check out complete sample project: https://github.com/kitty-project/kitty-sample 
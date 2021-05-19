package ex;

import com.julianjupiter.kitty.Kitty;
import com.julianjupiter.kitty.QueryParam;

public class App {
    public static void main(String[] args) {
        Kitty.create(router ->
                router
                        .get("/hello-world", (request, response) -> {
                            int id = request.queryParam("id").map(QueryParam::toInt).orElse(0);
                            String name = request.queryParam("name").map(QueryParam::toString).orElse(null);
                            return response.body("Hello, " + name + "! Your ID is " + id + ".");
                        })
        ).run();
    }
}

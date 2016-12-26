package requests;


        import io.restassured.http.ContentType;
        import io.restassured.response.Response;
        import io.restassured.response.ResponseBody;
        import models.ActorsModel;
        import models.Model;

        import static io.restassured.RestAssured.given;

/**
 * Created by solg on 21.12.2016.
 */
public class Requests {

    public static Response delete(String url, int id) {
        return given().
                pathParam("id", id).
                contentType(ContentType.JSON).
                when().
                delete(url +"/{id}");
    }

    public static Response post(Model actor, String url) {
        return given().contentType(ContentType.JSON).
                body(actor).
                when().
                post(url);

    }

    public static Response put(Model actor, String url, int id1) {
        return given().
                contentType(ContentType.JSON).
                pathParam("id", id1).
                body(actor).
                when().
                put(url +"/{id}");

    }

    public static Response getModel(String url, int id1) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("id", id1)
                .when()
                .get(url);
    }
}

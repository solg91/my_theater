package requests;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Model;

import static io.restassured.RestAssured.given;

/**
 * Created by solg on 21.12.2016.
 */
public class Requests {

    public static Response delete(String url, int id){
        return  given().
                contentType(ContentType.JSON).
                param("id", id).
                when().
                delete(url + "/" + id);
    }

    public static Response post(Model actor, String url) {
        return given().contentType(ContentType.JSON).
                body(actor).
                when().
                post(url);

    }

    public static Response put(Model actor, String url, int id ) {
        return given().
                contentType(ContentType.JSON).
                param("id", id).
                body(actor).
                when().
                put(url + "/"+ id);

    }

    public static Response getModel (String url, int id) {
        return given()
                .contentType(ContentType.JSON)
                .param("id", id)
                .when()
                .get(url +"/" + id);
    }
}

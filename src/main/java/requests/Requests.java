package requests;

import com.example.models.ActorsModel;
import com.example.models.Model;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Created by solg on 21.12.2016.
 */
public class Requests {

    public static int delete(String url, int id){
        return  given().
                contentType("application/json").
                when().
                delete(url + "/" + id).then().extract().statusCode();
    }

    public static int post(Model actor, String url) {
        return given().contentType(ContentType.JSON).
                body(actor).
                when().
                post(url).then().extract().statusCode();

    }

    public static int put(Model actor,String url, int id ) {
        return given().
                contentType(ContentType.JSON).
                body(actor).
                when().
                put(url + "/"+ id).then().extract().statusCode();

    }

    public static Response getModel (String url, int id) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(url +"/" + id);
    }
}

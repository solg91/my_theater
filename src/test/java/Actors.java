import com.example.models.ActorsModel;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by solg on 18.12.2016.
 */
public class Actors {

     public static ActorsModel getActorById(int id){
     ActorsModel actor = new ActorsModel();
        actor = when().get("/id").as(ActorsModel.class);
        return actor;

    }

    public static List<ActorsModel> getAllActors() {
        List<ActorsModel> listActors = Arrays.asList(
                when()
                        .get()
                        .then()
                        .extract().body().as(ActorsModel[].class));
        return listActors;
    }

    public static Integer deleteActorsById(int id){
              return  given().
                contentType("application/json").
                when().
                delete("/" + id).then().extract().statusCode();
    }

    public static Integer postActor(ActorsModel actor) {

        return given().contentType(ContentType.JSON).
                 body(actor).
                 when().
                 post().then().extract().statusCode();

    }

    public static Integer putActor(ActorsModel actor, int id ) {
               return given().
                contentType(ContentType.JSON).
                body(actor).
                when().
                put("/"+ id).then().extract().statusCode();



    }
    public static Integer getMaxActorsId() {

        List<ActorsModel> list = new ArrayList<ActorsModel>();
        list.addAll(getAllActors());

        int[] ar = new int[list.size()];

        for (int i = 0; i <= ar.length - 1; i++) {
            ar[i] = list.get(i).getId();
        }

        int maxIndex = 0;
        for (int i = 0; i < ar.length; i++) {
             if (maxIndex <ar[i]) {
                maxIndex = ar[i];
            }
        }
        return maxIndex;
    }


}

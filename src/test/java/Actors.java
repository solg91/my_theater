import com.example.models.ActorsModel;
import com.example.models.Model;
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
import static requests.Requests.getModel;

/**
 * Created by solg on 18.12.2016.
 */
public class Actors {


    public static ActorsModel getActorById( String url, int id){
        ActorsModel model = new ActorsModel();
        model = when().get(url +"/" +id).as(ActorsModel.class);
        return model;

    }

    public static List<ActorsModel> getAllActors(String url) {
        List<ActorsModel> listActors = Arrays.asList(
                when()
                        .get(url)
                        .then()
                        .extract().body().as(ActorsModel[].class));
        return listActors;
    }

    public static int getMaxActorsId(String url) {

        List<ActorsModel> list = new ArrayList<ActorsModel>();
        list.addAll(getAllActors(url));

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

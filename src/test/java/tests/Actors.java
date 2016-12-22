package tests;

import com.google.common.reflect.Parameter;
import com.google.gson.Gson;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import models.ActorsModel;
import org.testng.annotations.BeforeClass;

import java.util.*;

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


    public static ActorsModel getActorById(String url, int id){
         return when().get(url +"/" +id).as(ActorsModel.class);

    }

    public static List<ActorsModel> getAllActors(String url) {
        return Arrays.asList(
                when()
                        .get(url)
                        .then()
                        .extract().body().as(ActorsModel[].class));

    }



    public static int getMaxActorsId(String url) {

        List<ActorsModel> actorsModelList = new ArrayList<ActorsModel>();
        actorsModelList.addAll(getAllActors(url));

        ArrayList<Integer> arrayOfId = new ArrayList<Integer>();

        for (ActorsModel a: actorsModelList) {
            arrayOfId.add(a.getId());

        }
        return Collections.max(arrayOfId);
    }



}

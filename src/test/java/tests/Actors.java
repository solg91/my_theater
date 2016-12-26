package tests;

import io.restassured.http.ContentType;
import models.ActorsModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

/**
 * Created by solg on 18.12.2016.
 */
public class Actors {


    public static List<ActorsModel> getAllActors(String url) {
        return Arrays.asList(
                given().contentType(ContentType.JSON).
                when()
                        .get(url)
                        .then()
                        .extract().body().as(ActorsModel[].class));

    }

    public static int getMaxActorsId(String url) {

        List<ActorsModel> actorsModelList = new ArrayList<ActorsModel>();
        actorsModelList.addAll(getAllActors(url));

        ArrayList<Integer> arrayOfId = new ArrayList<Integer>();

        for (ActorsModel a : actorsModelList) {
            arrayOfId.add(a.getId());
        }
        return Collections.max(arrayOfId);
    }

}

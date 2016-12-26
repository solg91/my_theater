package tests;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import models.MusicalInstrumentsModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

/**
 * Created by solg on 19.12.2016.
 */
public class MusicalInstruments {

    public static List<MusicalInstrumentsModel> getAllInst(String url) {
        return Arrays.asList(
                when()
                        .get(url)
                        .then()
                        .extract().body().as(MusicalInstrumentsModel[].class));

    }

    public static Integer getMaxMusicalId(String url) {

        List<MusicalInstrumentsModel> instrumentsModelListlist = new ArrayList<MusicalInstrumentsModel>();
        instrumentsModelListlist.addAll(getAllInst(url));

        ArrayList<Integer> arrayOfId = new ArrayList<Integer>();
        for (MusicalInstrumentsModel a : instrumentsModelListlist) {
            arrayOfId.add(a.getId());
        }
        return Collections.max(arrayOfId);
    }
}

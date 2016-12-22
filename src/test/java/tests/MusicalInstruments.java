package tests;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import models.MusicalInstrumentsModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

/**
 * Created by solg on 19.12.2016.
 */
public class MusicalInstruments {

    public static MusicalInstrumentsModel getInstById(String url, int id){

        MusicalInstrumentsModel inst = new MusicalInstrumentsModel();
        inst = when().get(url + "/id").as(MusicalInstrumentsModel.class);
        return inst;

    }

    public static List<MusicalInstrumentsModel> getAllInst(String url) {
        List<MusicalInstrumentsModel> listInst = Arrays.asList(
                when()
                        .get(url)
                        .then()
                        .extract().body().as(MusicalInstrumentsModel[].class));
        return listInst;
    }


    public static Integer getMaxMusicalId(String url) {

        List<MusicalInstrumentsModel> list = new ArrayList<MusicalInstrumentsModel>();
        list.addAll(getAllInst(url));
        int[] ar = new int[list.size()];
        // while(itr.hasNext()){
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

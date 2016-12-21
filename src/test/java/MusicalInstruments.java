import com.example.models.ActorsModel;
import com.example.models.MusicalInstrumentsModel;
import com.google.gson.Gson;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

/**
 * Created by solg on 19.12.2016.
 */
public class MusicalInstruments {

    public static MusicalInstrumentsModel getInstById(int id){

        MusicalInstrumentsModel inst = new MusicalInstrumentsModel();
        inst = when().get("/id").as(MusicalInstrumentsModel.class);
        return inst;

    }

    public static List<MusicalInstrumentsModel> getAllInst() {
        List<MusicalInstrumentsModel> listInst = Arrays.asList(
                when()
                        .get()
                        .then()
                        .extract().body().as(MusicalInstrumentsModel[].class));
        return listInst;
    }

    public static Integer deleteInstById(int id){
        return  given().
                contentType("application/json").
                when().
                delete("/" + id).then().extract().statusCode();
    }

    public static Integer postInst(MusicalInstrumentsModel inst) {
        Gson gson = new Gson();
        String user = gson.toJson(inst);
        return given().contentType(ContentType.JSON).
                body(user).
                when().
                post().then().extract().statusCode();

    }

    public static Integer putInst(MusicalInstrumentsModel inst, int id ) {
        Gson gson = new Gson();
        String user = gson.toJson(inst);
        String s;
        return given().
                contentType(ContentType.JSON).
                body(user).
                when().
                put("/"+ id).then().extract().statusCode();

    }
    public static Integer getMaxMusicalId() {

        List<MusicalInstrumentsModel> list = new ArrayList<MusicalInstrumentsModel>();
        list.addAll(getAllInst());
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

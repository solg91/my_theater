package tests;


import io.restassured.RestAssured;
import models.MusicalInstrumentsModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static requests.Requests.post;

/**
 * Created by solg on 20.12.2016.
 */
public class MusicalInstrumentsTest extends MusicalInstruments {

    public final static String INSTURL = "http://localhost:3000/musicalInstruments";


    @Test
    public static void addUniqueInstrumentTest() {

       MusicalInstrumentsModel inst = new MusicalInstrumentsModel();

        if (MusicalInstruments.getAllInst(INSTURL).isEmpty()) {
            inst.setId(1);
        } else {
            inst.setId(MusicalInstruments.getMaxMusicalId(INSTURL) + 1);
        }

        inst.setName("MyMusicalInst");
        inst.setPlayID(4);

        //assertThat("Error with adding actors", post(inst, INSTURL), is(201));
    }

}

package tests;


import io.restassured.RestAssured;
import models.ActorsModel;
import models.MusicalInstrumentsModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static requests.Requests.delete;
import static requests.Requests.getModel;
import static requests.Requests.post;

/**
 * Created by solg on 20.12.2016.
 */
public class MusicalInstrumentsTest extends MusicalInstruments {

    public final static String INSTRUMENTS_URL = "http://localhost:3000/musicalInstruments";


    @Test
    public void addUniqueInstrumentTest() {

       MusicalInstrumentsModel musicalInstrumentsModelinst = new MusicalInstrumentsModel();

        if (MusicalInstruments.getAllInst(INSTRUMENTS_URL).isEmpty()) {
            musicalInstrumentsModelinst.setId(1);
        } else {
            musicalInstrumentsModelinst.setId(MusicalInstruments.getMaxMusicalId(INSTRUMENTS_URL) + 1);
        }

        musicalInstrumentsModelinst.setName("MyMusicalInst");
        musicalInstrumentsModelinst.setPlayID(4);

        int id= post(musicalInstrumentsModelinst, INSTRUMENTS_URL).as(MusicalInstrumentsModel.class).getId();
        List<MusicalInstrumentsModel> musicalInstrumentsBeforeUpdate = Arrays.asList(getModel(INSTRUMENTS_URL,id).as(MusicalInstrumentsModel[].class));
        //check correct Name
        assertThat(musicalInstrumentsModelinst.getName(), is(musicalInstrumentsBeforeUpdate.get(0).getName()));
    }

    @Test
    public void deleteInstrumentTest() {

        //addUniqueInstrumentTest();
        int maxActorId = getMaxMusicalId(INSTRUMENTS_URL);
        assertThat(delete(INSTRUMENTS_URL, maxActorId).statusCode(), is(200));
        //check null ID  for deleted actor
        List<MusicalInstrumentsModel> instrumentAfterDelete = Arrays.asList(getModel(INSTRUMENTS_URL, maxActorId).as(MusicalInstrumentsModel[].class));
        assertThat(instrumentAfterDelete.isEmpty(), is(true));
    }

}

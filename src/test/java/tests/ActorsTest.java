package tests;

import com.google.gson.Gson;
import io.restassured.response.Response;
import models.ActorsModel;
import models.EducationModel;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;
import static requests.Requests.*;

/**
 * Created by solg on 19.12.2016.
 */
public class ActorsTest extends Actors {

    public final static String ACTORS_URL = "http://localhost:3000/actors";

    @Test
    public void addUniqueActorTest() {

        //create new actor model
        List listMusicalInstruments = Arrays.asList(5, 10);
        List listPreviousPlay = Arrays.asList(5);

        EducationModel educationModel = new EducationModel();
        educationModel.setName("Uiniversity Alberta Nobel");
        educationModel.setFaculty("acting skills");
        educationModel.setYearOfEnding("2010");

        ActorsModel actorsModel = new ActorsModel();
        int actorModelId = 0;
        if (getAllActors(ACTORS_URL).isEmpty()) {
            actorModelId = 1;
        } else {
            actorModelId = (getMaxActorsId(ACTORS_URL) + 1);
        }
        actorsModel.setId(actorModelId);
        actorsModel.setName("Marina Nazarenko");
        actorsModel.setEmail("marina@gmail.com");
        actorsModel.setCurrentPlay(5);
        actorsModel.setMusicalInstruments(listMusicalInstruments);
        actorsModel.setPreviousPlay(listPreviousPlay);
        actorsModel.setPhone("1212.1231.123");
        actorsModel.setSize(38);
        actorsModel.setEducation(educationModel);


        int id = post(actorsModel, ACTORS_URL).as(ActorsModel.class).getId();
        //check correct Name
        List<ActorsModel> actorsAfterAdd = Arrays.asList(getModel(ACTORS_URL,id).as(ActorsModel[].class));
        assertThat(actorsModel.getName(), is(actorsAfterAdd.get(0).getName()));
    }

    @Test
    public void addNotUnigueActorTest() {

        List listMusicalInstruments = Arrays.asList(5, 10);
        List listPreviousPlay = Arrays.asList(5);

        EducationModel educationModel = new EducationModel();
        educationModel.setName("Uiniversity Alberta Nobel");
        educationModel.setFaculty("acting skills");
        educationModel.setYearOfEnding("2010");

        ActorsModel actorsModel = new ActorsModel();
        actorsModel.setId(getMaxActorsId(ACTORS_URL));
        actorsModel.setName("Marina Nazarenko");
        actorsModel.setEmail("marina@gmail.com");
        actorsModel.setCurrentPlay(5);
        actorsModel.setMusicalInstruments(listMusicalInstruments);
        actorsModel.setPreviousPlay(listPreviousPlay);
        actorsModel.setPhone("1212.1231.123");
        actorsModel.setSize(38);
        actorsModel.setEducation(educationModel);

        //check that actor was not added
        assertThat("User was added successfully", post(actorsModel, ACTORS_URL).statusCode(), is(500));
    }


    @Test
    public void updateExistingActorTest() {

        int maxActorId = getMaxActorsId(ACTORS_URL);

        List<ActorsModel> actorsBeforeUpdate = Arrays.asList(getModel(ACTORS_URL, maxActorId).as(ActorsModel[].class));

        List<ActorsModel> actorsforUpdate = Arrays.asList(getModel(ACTORS_URL, maxActorId).as(ActorsModel[].class));
        ActorsModel actorsModel = actorsforUpdate.get(0);
        actorsModel.setName("Ivan Egorovich");
        actorsModel.setEmail("ivan@gmail.com");
        actorsModel.setCurrentPlay(1);

        put(actorsModel, ACTORS_URL, actorsModel.getId());
        List<ActorsModel> actorsAfterUpdate = Arrays.asList(getModel(ACTORS_URL, maxActorId).as(ActorsModel[].class));

        //check correct Name/Email/Current Play
        assertThat(actorsAfterUpdate.get(0).getName(), is(not(actorsBeforeUpdate.get(0).getName())));
        assertThat(actorsAfterUpdate.get(0).getEmail(), is(not(actorsBeforeUpdate.get(0).getEmail())));
        assertThat(actorsAfterUpdate.get(0).getCurrentPlay(), is(not(actorsBeforeUpdate.get(0).getCurrentPlay())));

    }

    @Test
    public void findAllActorsWithMusicalInstruments2or6() {

        List listInstruments = Arrays.asList(2, 6);

        List<ActorsModel> listActorsWithInstruments = Arrays.asList(
                given().
                        param("musicalInstruments", listInstruments).
                        when()
                        .get(ACTORS_URL)
                        .then()
                        .extract().body().as(ActorsModel[].class));

        //check empty List
        assertThat("Empty list", listActorsWithInstruments.isEmpty(), is(false));

        //check instruments for actor from search
        for (ActorsModel a : listActorsWithInstruments) {
            assertThat(a.getMusicalInstruments(), anyOf(hasItem(2), hasItem(6)));
        }

    }

    @Test
    public void findAllActorsWithSizeAndCurrentPlays() {
        int size = 50;
        int currentPlay = 1;

        List<ActorsModel> listActorsAfterSearch = Arrays.asList(
                given().
                        param("size", size).
                        param("currentPlay", currentPlay).
                        when()
                        .get(ACTORS_URL)
                        .then()
                        .extract().body().as(ActorsModel[].class));

        //check empty List
        assertThat("Empty list", listActorsAfterSearch.isEmpty(), is(false));
        //check parametrs for actor from search

        for (ActorsModel a : listActorsAfterSearch) {
            assertThat(a.getSize(), is(size));
            assertThat(a.getCurrentPlay(), is(currentPlay));
        }

    }

    @Test
    public void addNewInstrumentsToUser() {

        int newInst = 9;
        List listInstToAdd = Arrays.asList(newInst);

        int maxActorId = getMaxActorsId(ACTORS_URL);

        List<ActorsModel> actorsBeforeUpdate = Arrays.asList(getModel(ACTORS_URL, maxActorId).as(ActorsModel[].class));

        List<ActorsModel> actorsforUpdate = Arrays.asList(getModel(ACTORS_URL, maxActorId).as(ActorsModel[].class));
        ActorsModel actorsModel = actorsforUpdate.get(0);
        actorsModel.setMusicalInstruments(listInstToAdd);

        Response response = put(actorsModel, ACTORS_URL, actorsModel.getId());
        List<ActorsModel> actorsAfterUpdate = Arrays.asList(getModel(ACTORS_URL, maxActorId).as(ActorsModel[].class));

        //check correct MusicalInstruments
        assertThat(actorsAfterUpdate.get(0).getMusicalInstruments(), is(not(actorsBeforeUpdate.get(0).getMusicalInstruments())));
    }

    @Test
    public void deleteActorTest() {

        //addUniqueActorTest();
        int maxActorId = getMaxActorsId(ACTORS_URL);
        assertThat(delete(ACTORS_URL, maxActorId).statusCode(), is(200));
        //check null ID  for deleted actor
        List<ActorsModel> actorsAfterDelete = Arrays.asList(getModel(ACTORS_URL, maxActorId).as(ActorsModel[].class));
        assertThat(actorsAfterDelete.isEmpty(), is(true));

    }


}

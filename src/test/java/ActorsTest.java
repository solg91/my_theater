import com.example.models.ActorsModel;
import com.example.models.EducationModel;
import com.example.models.MusicalInstrumentsModel;
import io.restassured.RestAssured;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by solg on 19.12.2016.
 */
public class ActorsTest extends Actors {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
        RestAssured.basePath = "/actors";
    }

    @Test
    public static void addUniqueActorTest() {

        List<Integer> listMusicalInstruments = new ArrayList<Integer>();
        listMusicalInstruments.add(5);
        listMusicalInstruments.add(10);

        List<Integer> previosPlay = new ArrayList<Integer>();
        previosPlay.add(5);

        EducationModel educationModel = new EducationModel();
        educationModel.setName("Uiniversity Alberta Nobel");
        educationModel.setFaculty("acting skills");
        educationModel.setYearOfEnding("2010");

        ActorsModel actorsModel = new ActorsModel();

        if (getAllActors().isEmpty()) {
            actorsModel.setId(1);
        } else {
            actorsModel.setId(getMaxActorsId() + 1);
        }

        actorsModel.setName("Marina Nazarenko");
        actorsModel.setEmail("marina@gmail.com");
        actorsModel.setCurrentPlay(5);
        actorsModel.setMusicalInstruments(listMusicalInstruments);
        actorsModel.setPreviousPlay(previosPlay);
        actorsModel.setPhone("1212.1231.123");
        actorsModel.setSize(38);
        actorsModel.setEducation(educationModel);

        assertThat("Error with adding actors", postActor(actorsModel), is(201));
    }

    @Test
    public static void addNotUnigueActorTest() {

        List<Integer> listMusicalInstruments = new ArrayList<Integer>();
        listMusicalInstruments.add(5);

        List<Integer> previosPlay = new ArrayList<Integer>();
        previosPlay.add(5);

        EducationModel educationModel = new EducationModel();
        educationModel.setName("Uiniversity Alberta Nobel");
        educationModel.setFaculty("acting skills");
        educationModel.setYearOfEnding("2010");

        ActorsModel actorsModel = new ActorsModel();
        actorsModel.setId(getMaxActorsId());
        actorsModel.setName("Marina Nazarenko");
        actorsModel.setEmail("marina@gmail.com");
        actorsModel.setCurrentPlay(5);
        actorsModel.setMusicalInstruments(listMusicalInstruments);
        actorsModel.setPreviousPlay(previosPlay);
        actorsModel.setPhone("1212.1231.123");
        actorsModel.setSize(38);
        actorsModel.setEducation(educationModel);

        postActor(actorsModel);
        assertThat("User was added successfully", postActor(actorsModel), is(500));
    }



    @Test
    public static void updateExistingActorTest() {
        assertThat("List is empty", getAllActors(), is(not(nullValue())));

        List<Integer> listMusicalInstruments = new ArrayList<Integer>();
        listMusicalInstruments.add(3);
        listMusicalInstruments.add(7);

        List<Integer> previosPlay = new ArrayList<Integer>();
        previosPlay.add(2);
        previosPlay.add(8);

        EducationModel educationModel = new EducationModel();
        educationModel.setName("Uiniversity Alberta Nobel");
        educationModel.setFaculty("acting skills");
        educationModel.setYearOfEnding("2010");

        ActorsModel actorsModel = new ActorsModel();
        actorsModel.setId(getMaxActorsId());
        actorsModel.setName("Ivan Egorovich");
        actorsModel.setEmail("ivan@gmail.com");
        actorsModel.setCurrentPlay(1);
        actorsModel.setMusicalInstruments(listMusicalInstruments);
        actorsModel.setPreviousPlay(previosPlay);
        actorsModel.setPhone("6767678686");
        actorsModel.setSize(46);
        actorsModel.setEducation(educationModel);


        assertThat("error with updating user", putActor(actorsModel,getMaxActorsId()), is(200));

        assertThat("Name",getActorById(getMaxActorsId()).getName(), is(not("Marina Nazarenko")));
        assertThat("Email",getActorById(getMaxActorsId()).getEmail(), is(not("marina@gmail.com")));
        assertThat("CurrentPlay",getActorById(getMaxActorsId()).getCurrentPlay(), is(not(5)));
        assertThat("PreviousPlay",getActorById(getMaxActorsId()).getPreviousPlay(), not(hasItems(equalTo(5))));
        assertThat("MusicalInstruments",getActorById(getMaxActorsId()).getMusicalInstruments(), not(hasItems(equalTo(5))));
        assertThat("Phone",getActorById(getMaxActorsId()).getPhone(), is(not("1212.1231.123")));
        assertThat("Size",getActorById(getMaxActorsId()).getSize(), is(not(38)));

    }

    @Test
    public static void findAllActorsWithMusicalInstruments2or6(){

        List<Integer> listInst = new ArrayList<Integer>();
        listInst.add(2);
        listInst.add(6);
        List<ActorsModel> listActorsWithInstruments = Arrays.asList(
                given().
                        param("musicalInstruments", listInst).
                        when()
                        .get("/")
                        .then()
                        .extract().body().as(ActorsModel[].class));

        assertThat("Wrong actor", listActorsWithInstruments.get(0).getName(), is("Olga Karpova"));

    }

    @Test
    public static void findAllActorsWithSizeAndCurrentPlays(){
        int size = 50;
        int currentPlay = 1;
        List<ActorsModel> listActorsWithInstruments = Arrays.asList(
                given().
                        param("size", size).
                        param("currentPlay", currentPlay ).
                        when()
                        .get("/")
                        .then()
                        .extract().body().as(ActorsModel[].class));

        assertThat("Wrong actor", listActorsWithInstruments.get(0).getName(), is("Nicholas Runolfsdottir V"));

    }

    @Test
    public static void addNewInstrumentsToUser () {

        int newInst = 6;
        List<Integer> listInstToAdd = new ArrayList<Integer>();
        listInstToAdd.add(newInst);

        List<Integer> previosPlay = new ArrayList<Integer>();
        previosPlay.add(2);
        previosPlay.add(8);

        EducationModel educationModel = new EducationModel();
        educationModel.setName("Uiniversity Alberta Nobel");
        educationModel.setFaculty("acting skills");
        educationModel.setYearOfEnding("2010");

        ActorsModel actorsModel = new ActorsModel();
        actorsModel.setId(7);
        actorsModel.setName("Ivan Egorovich");
        actorsModel.setEmail("ivan@gmail.com");
        actorsModel.setCurrentPlay(1);

        actorsModel.setPreviousPlay(previosPlay);
        actorsModel.setPhone("6767678686");
        actorsModel.setSize(46);
        actorsModel.setEducation(educationModel);

           actorsModel.setMusicalInstruments(listInstToAdd);


        assertThat("error with updating user",putActor(actorsModel, 7), is(200));
    }

    @Test
    public static void deleteActorTest() {
        assertThat("List is empty", getAllActors(), is(not(nullValue())));

        List<Integer> listMusicalInstruments = new ArrayList<Integer>();
        listMusicalInstruments.add(5);
        listMusicalInstruments.add(10);

        List<Integer> previosPlay = new ArrayList<Integer>();
        previosPlay.add(5);

        EducationModel educationModel = new EducationModel();
        educationModel.setName("Uiniversity Alberta Nobel");
        educationModel.setFaculty("acting skills");
        educationModel.setYearOfEnding("2010");

        ActorsModel actorsModel = new ActorsModel();

        if (getAllActors().isEmpty()) {
            actorsModel.setId(1);
        } else {
            actorsModel.setId(getMaxActorsId() + 1);
        }

        actorsModel.setName("Marina Nazarenko");
        actorsModel.setEmail("marina@gmail.com");
        actorsModel.setCurrentPlay(5);
        actorsModel.setMusicalInstruments(listMusicalInstruments);
        actorsModel.setPreviousPlay(previosPlay);
        actorsModel.setPhone("1212.1231.123");
        actorsModel.setSize(38);
        actorsModel.setEducation(educationModel);
        postActor(actorsModel);

        deleteActorsById(getMaxActorsId() + 1);
        assertThat("Wrong delete operation", deleteActorsById(getMaxActorsId()), is(200));
    }


}

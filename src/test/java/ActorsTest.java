import com.example.models.ActorsModel;
import com.example.models.EducationModel;
import org.testng.annotations.Test;

import java.util.ArrayList;
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

    public final static String ACTORSURL = "http://localhost:3000/actors";

    @Test
    public static void addUniqueActorTest() {
        //текущий размер списка юзеров
        int currentsize = getAllActors(ACTORSURL).size();

        //создаем модельнового юзера
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

        if (getAllActors(ACTORSURL).isEmpty()) {
            actorsModel.setId(1);
        } else {
            actorsModel.setId(getMaxActorsId(ACTORSURL) + 1);
        }

        actorsModel.setName("Marina Nazarenko");
        actorsModel.setEmail("marina@gmail.com");
        actorsModel.setCurrentPlay(5);
        actorsModel.setMusicalInstruments(listMusicalInstruments);
        actorsModel.setPreviousPlay(previosPlay);
        actorsModel.setPhone("1212.1231.123");
        actorsModel.setSize(38);
        actorsModel.setEducation(educationModel);

        //проверяем код ответа
        assertThat("Error with adding actors", post(actorsModel, ACTORSURL), is(201));
        //сравниваем текущий и предыдущий размер списка
        assertThat("Incorrect size of list", getAllActors(ACTORSURL).size(), is(not(currentsize)));
    }

    @Test
    public static void addNotUnigueActorTest() {

        //текущий размер списка юзеров
        int currentsize = getAllActors(ACTORSURL).size();

        List<Integer> listMusicalInstruments = new ArrayList<Integer>();
        listMusicalInstruments.add(5);

        List<Integer> previosPlay = new ArrayList<Integer>();
        previosPlay.add(5);

        EducationModel educationModel = new EducationModel();
        educationModel.setName("Uiniversity Alberta Nobel");
        educationModel.setFaculty("acting skills");
        educationModel.setYearOfEnding("2010");

        ActorsModel actorsModel = new ActorsModel();
        actorsModel.setId(getMaxActorsId(ACTORSURL));
        actorsModel.setName("Marina Nazarenko");
        actorsModel.setEmail("marina@gmail.com");
        actorsModel.setCurrentPlay(5);
        actorsModel.setMusicalInstruments(listMusicalInstruments);
        actorsModel.setPreviousPlay(previosPlay);
        actorsModel.setPhone("1212.1231.123");
        actorsModel.setSize(38);
        actorsModel.setEducation(educationModel);

        //post(actorsModel, ACTORSURL);
        assertThat("User was added successfully", post(actorsModel, ACTORSURL), is(500));
        //сравниваем текущий и предыдущий размер списка
        assertThat("Incorrect size of list", getAllActors(ACTORSURL).size(), is(currentsize));
    }


    @Test
    public static void updateExistingActorTest() {

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
        actorsModel.setId(getMaxActorsId(ACTORSURL));
        actorsModel.setName("Ivan Egorovich");
        actorsModel.setEmail("ivan@gmail.com");
        actorsModel.setCurrentPlay(1);
        actorsModel.setMusicalInstruments(listMusicalInstruments);
        actorsModel.setPreviousPlay(previosPlay);
        actorsModel.setPhone("6767678686");
        actorsModel.setSize(46);
        actorsModel.setEducation(educationModel);

        //проверяем статус ответа
        assertThat("error with updating user", put(actorsModel, ACTORSURL, getMaxActorsId(ACTORSURL)), is(200));

        //сравниваем значение полей до и после обновления
        assertThat("Name", getActorById(ACTORSURL, getMaxActorsId(ACTORSURL)).getName(), is(not("Marina Nazarenko")));
        assertThat("Email", getActorById(ACTORSURL, getMaxActorsId(ACTORSURL)).getEmail(), is(not("marina@gmail.com")));
        assertThat("CurrentPlay", getActorById(ACTORSURL, getMaxActorsId(ACTORSURL)).getCurrentPlay(), is(not(5)));
        assertThat("PreviousPlay", getActorById(ACTORSURL, getMaxActorsId(ACTORSURL)).getPreviousPlay(), not(hasItems(equalTo(5))));
        assertThat("MusicalInstruments", getActorById(ACTORSURL, getMaxActorsId(ACTORSURL)).getMusicalInstruments(), not(hasItems(equalTo(5))));
        assertThat("Phone", getActorById(ACTORSURL, getMaxActorsId(ACTORSURL)).getPhone(), is(not("1212.1231.123")));
        assertThat("Size", getActorById(ACTORSURL, getMaxActorsId(ACTORSURL)).getSize(), is(not(38)));

    }

    @Test
    public static void findAllActorsWithMusicalInstruments2or6() {

        List<Integer> listInst = new ArrayList<Integer>();
        listInst.add(2);
        listInst.add(6);

        List<ActorsModel> listActorsWithInstruments = Arrays.asList(
                given().
                        param("musicalInstruments", listInst).
                        when()
                        .get(ACTORSURL + "/")
                        .then()
                        .extract().body().as(ActorsModel[].class));

        //проверяем, что список не пустой
        assertThat("Empty list", listActorsWithInstruments, notNullValue());
        //проверяеи имя найденного актера
        assertThat("Wrong actor", listActorsWithInstruments.get(0).getName(), is("Olga Karpova"));

    }

    @Test
    public static void findAllActorsWithSizeAndCurrentPlays() {
        int size = 50;
        int currentPlay = 1;

        List<ActorsModel> listActorsWithInstruments = Arrays.asList(
                given().
                        param("size", size).
                        param("currentPlay", currentPlay).
                        when()
                        .get(ACTORSURL + "/")
                        .then()
                        .extract().body().as(ActorsModel[].class));

        //проверяем, что список не пустой
        assertThat("Empty list", listActorsWithInstruments, notNullValue());

        //проверяеи имя найденного актера
        assertThat("Wrong actor", listActorsWithInstruments.get(0).getName(), is("Nicholas Runolfsdottir V"));

    }

    @Test
    public static void addNewInstrumentsToUser() {

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

        assertThat("error with updating user", put(actorsModel, ACTORSURL, 7), is(200));
    }

    @Test
    public static void deleteActorTest() {

        addUniqueActorTest();
        //проверяем код ответа
        assertThat(delete(ACTORSURL, getMaxActorsId(ACTORSURL)), is(200));
    }


}

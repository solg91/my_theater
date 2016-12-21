import com.example.models.ActorsModel;
import com.example.models.EducationModel;
import com.example.models.MusicalInstrumentsModel;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by solg on 20.12.2016.
 */
public class MusicalInstrumentsTest extends MusicalInstruments{

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
        RestAssured.basePath = "/musicalInstruments";
    }

    @Test
    public static void addUniqueInstrumentTest() {

       MusicalInstrumentsModel inst = new MusicalInstrumentsModel();

        if (getAllInst().isEmpty()) {
            inst.setId(1);
        } else {
            inst.setId(getMaxMusicalId() + 1);
        }

        inst.setName("MyMusicalInst");
        inst.setPlayID(4);

        assertThat("Error with adding actors", postInst(inst), is(201));
    }

}

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class JSONSchemaValidation {

    @Test
    public void JSONSchemaValid()
    {
        given()

                .when()
                .get("http://localhost:3000/Store")

                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchemaValidation.json"));

    }
}

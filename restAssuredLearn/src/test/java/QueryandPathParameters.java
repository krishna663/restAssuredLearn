import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class QueryandPathParameters {

    //Eg url: https://reqres.in/api/users?page=2
    // https://reqres.in -- domain
    // api/users -- path
    // after question mark - ?page=2 -- Query

    @Test
    void Queryandpathparameter()
    {
        given()
                .header("x-api-key","reqres-free-v1")
                .pathParams("Mypath","users")
                .queryParam("page",2)
                .queryParam("id",8)
                //Path params we need to add in the url - whereas query param is not required.. once we created it will look for those query params
                .when()
                .get("https://reqres.in/api/{Mypath}")     // path params will act like a variable, we can hardcode the variable here

                .then()
                .statusCode(200)
                .log().all();
    }
}

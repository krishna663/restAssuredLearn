package chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetUser {

    @Test
    public void getUser(ITestContext context)
    {
//        int id= 8031516; // hardcoding the value now - but, we should get the id value from create method
        int id = (Integer) context.getAttribute("user_id");
        //The above can be used if all test methods are present under one test // if you need to run at suite level.. then modify the code as
        //        // int id = context.getsuite().getattribute("user_id");

        String bearerToken = "db8d1382f3ea3f034d302d7b390b461211fb558f285e36789197c8c40b515068";

        given()
                .queryParam("id",id)
                .header("Authorization", "Bearer "+bearerToken)

                .when()
                .get("https://gorest.co.in/public/v2/users/")

                .then()
                .statusCode(200)
                .log().body();
    }

}

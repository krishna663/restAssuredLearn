package chaining;

import net.datafaker.Faker;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    @Test
    public void updateUser(ITestContext context) {
        Faker faker = new Faker();
        PojoClassforChaining obj = new PojoClassforChaining();
        obj.setEmail(faker.internet().emailAddress());
        obj.setName(faker.name().fullName());
        obj.setGender("Male");
        obj.setStatus("Inactive");

        String bearerToken = "db8d1382f3ea3f034d302d7b390b461211fb558f285e36789197c8c40b515068";

        //        int id= 8031516; // hardcoding the value now - but, we should get the id value from create method
        int id = (Integer) context.getAttribute("user_id");
        //The above can be used if all test methods are present under one test // if you need to run at suite level.. then modify the code as
        // int id = context.getsuite().getattribute("user_id");

        given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType("application/json")
                .pathParam("id", id)
                .body(obj)

                .when()
                .put("https://gorest.co.in/public/v2/users/{id}")

                .then()
                .statusCode(200)
                .log().body();
    }
}

package chaining;

import net.datafaker.Faker;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUser {

    @Test
    void tes_createUser(ITestContext context)
    {
        Faker faker = new Faker();
        PojoClassforChaining obj = new PojoClassforChaining();
        obj.setEmail(faker.internet().emailAddress());
        obj.setName(faker.name().fullName());
        obj.setGender("Male");
        obj.setStatus("Active");

        String bearerToken = "db8d1382f3ea3f034d302d7b390b461211fb558f285e36789197c8c40b515068";

        int id = given()
                .headers("Authorization", "Bearer "+bearerToken)
                .contentType("application/json")
                .body(obj)

                .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");

        System.out.println("Id is "+id);
        context.setAttribute("user_id", id);
        // if you need to set this attribute at the suite level then modify the code as
        // context.getsuite().setattribute("user_id",id);
    }

}

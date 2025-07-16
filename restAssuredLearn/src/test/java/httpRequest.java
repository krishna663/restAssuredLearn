import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class httpRequest {
    int id;

    @Test(priority = 1)
    public void getUsers()
    {

                when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();

    }
    @Test(priority = 2)
    void createUser()
    {
        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("name","Krish");
        hm.put("job","QA");

        id=given()
                .header("x-api-key","reqres-free-v1")
                .contentType("application/json")
                .body(hm)

                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

//                .then()
//                .statusCode(201)
//                .log().all();
    }

    @Test (priority = 3, dependsOnMethods = {"createUser"})
    void updateUser()
    {
        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("name","Krish");
        hm.put("job","Senior QA");

        given()
                .header("x-api-key","reqres-free-v1")
                .contentType("application/json")
                .body(hm)

                .when()
                .put("https://reqres.in/api/users/"+id)

                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    void delete()
    {
        given()
                .header("x-api-key","reqres-free-v1")

                .when()
                .delete("https://reqres.in/api/users/"+id)

                .then()
                .statusCode(204)
                .log().all();
    }
}

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class DiffwaystoCreatePostRequest {
    //using HashMap which we seen already in httprequest method
    //post request using org.json body creation -- this is similar to HashMap,
    //using POJO - mostly used
    //using external json file data
    Object id;

    @Test(priority = 1)
    void usingJsonObject()
    {
        //add required json dependency
        JSONObject obj = new JSONObject();
        obj.put("Name","Krish");
        obj.put("Role","QA");
        obj.put("Age",25);

        String Coursesarr[] = {"C","C++","Java"};
        obj.put("Courses",Coursesarr);

        id=given()
                .contentType("application/json")
                // converting into String format when we use org.json object
                .body(obj.toString())

                .when()
                .post("http://localhost:3000/students")
                .jsonPath().get("id");

//                .then()
//                .statusCode(201)
//                .body("Name",equalTo("Krish"))
//                .body("Role",equalTo("QA"))
//                .body("Age",equalTo(25))
//                .body("Courses[0]",equalTo("C"))
//                .body("Courses[1]",equalTo("C++"))
//                .body("Courses[2]",equalTo("Java"))
//               // .header("Content-type","application/json; charset=utf-8")
//                .log().all();

    }
    @Test(priority = 2, dependsOnMethods = {"usingJsonObject"})
    void deleteUser()
    {
        given()

                .when()
                .delete("http://localhost:3000/students/"+id)

                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 3)
    void usingPOJOClass()
    {
        usingPOJO pojo = new usingPOJO();
        String courses[] = {"JS","Python"};
        pojo.setName("Karthi");
        pojo.setLocation("US");
        pojo.setPhone("742802282");
        pojo.setCourses(courses);

        given()
                .contentType("application/json")
                .body(pojo)

                .when()
                .post("http://localhost:3000/students")


                .then()
                .statusCode(201)
                .body("name",equalTo("Karthi"))
                .body("location",equalTo("US"))
                .body("phone",equalTo("742802282"))
                .body("courses[0]",equalTo("JS"))
                .body("courses[1]",equalTo("Python"))
               // .header("Content-type","application/json; charset=utf-8")
                .log().all();
    }

    @Test(priority = 4)
    public void usingExternalJsonFile() throws FileNotFoundException {
        File file = new File(".\\students.json");
        FileReader fr = new FileReader(file);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject obj = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(obj.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name",equalTo("John"))
                .body("location",equalTo("India"))
                .body("phone",equalTo("1234567890"))
                .body("courses[0]", equalTo("Java"))
                .body("courses[1]", equalTo("Selenium"))
                .log().all();

    }
}

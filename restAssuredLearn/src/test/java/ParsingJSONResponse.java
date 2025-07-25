import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ParsingJSONResponse {

    @Test
    void ParsingJSONResponseData()
    {
        Response res = given()

                .when()
                .get("http://localhost:3000/Store");

        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertEquals(res.header("Content-Type"), "application/json");
        Assert.assertEquals((res.jsonPath().get("book[2].title").toString()),"The Hitchhiker's Guide to the Galaxy");

        JSONObject obj = new JSONObject(res.getBody().asString());

        // to get all value of a jsonarray - here we retrieved all title values
        double totalprice = 0;

        for(int i=0; i<obj.getJSONArray("book").length(); i++)
        {
            String booktitle = obj.getJSONArray("book").getJSONObject(i).get("title").toString();
            String bookprice = obj.getJSONArray("book").getJSONObject(i).get("price").toString();
            System.out.println("Book name"+booktitle+" book price ="+bookprice);
            totalprice = Double.parseDouble(bookprice) + totalprice;

        }

        System.out.println("Total price " +totalprice);

    }

}

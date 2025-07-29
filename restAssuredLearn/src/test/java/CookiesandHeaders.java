import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookiesandHeaders {

    //There is two approach - if we know headers and cookies name we can print their values directly by using getcookie and getheader methods
    // 2nd approach is to get all cookies and headers by using getcookies and getheaders method

    @Test
    void cookiesandHeaders()
    {
        Response res=given()

                .when()
                .get("https://www.google.com/");

        //printing cookie value and header value directly if we know the name
                String cookie_value = res.getCookie("AEC");
                String header_value = res.getHeader("Content-Encoding");
                System.out.println(cookie_value);
                System.out.println(header_value);

                // fetchig all cookie and header values..

        Map<String,String> cookie_values = res.getCookies();

        for(String k :cookie_values.keySet())
        {
            System.out.println("Cookies values are"+res.getCookie(k));
        }

        Headers header_values= res.getHeaders();

        // but this is not recommended because.. if we give log.headers() it will print all headers.. simple
        for (Header h : header_values)
        {
            System.out.println("Header values are"+ h.getValue());
        }

    }
}

package src.main.java.RestAPI.RestAPITesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Hello world!
 */
public class App {
    @Test
    public void getmethod() {

        RestAssured.baseURI = "https://maps.google.apis.com";

        given().
                param("").
                when().get("").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
                and().body("results[0].geometry.Location.lat", equalTo("-33.46646434"));
    }

    @Test
    public void postmethod() {
        RestAssured.baseURI = "http://216.10.245.166/maps/api/place/add/json";
        given().
                queryParam("key", "qaclick123").
                body("{\r\n" +
                        "    \"location\":{\r\n" +
                        "        \"lat\" : -38.383494,\r\n" +
                        "        \"lng\" : 33.427362\r\n" +
                        "    },\r\n" +
                        "    \"accuracy\":50,\r\n" +
                        "    \"name\":\"Frontline house\",\r\n" +
                        "    \"phone_number\":\"(+91) 983 893 3937\",\r\n" +
                        "    \"address\" : \"29, side layout, cohen 09\",\r\n" +
                        "    \"types\": [\"shoe park\",\"shop\"],\r\n" +
                        "    \"website\" : \"http://google.com\",\r\n" +
                        "    \"language\" : \"French-IN\"\r\n" +
                        "}\r\n" +
                        "").
                when().post("/maps/api/place/add/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
                and().body("status", equalTo("OK"));
    }
}

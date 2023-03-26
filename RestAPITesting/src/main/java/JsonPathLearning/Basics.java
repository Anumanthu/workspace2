package src.main.java.JsonPathLearning;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import src.files.ReUsableMethods;
import src.files.payload;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.sessionId;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
// validate if Add Place API is workimg as expected 
        //Add place-> Update Place with New Address -> Get Place to validate if New address is present in response

        //given - all input details
        //when - Submit the API -resource,http method
        //Then - validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(payload.AddPlace())
                .when().post("maps/api/place/add/json")
                .then().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();


//		RestAssured.baseURI="https://rahulshettyacademy.com";

//		String res=given().log().all().queryParam("key","qlick123").header("content-type","application/json").contentType("application/json")
//		.body("payload")
//		.when().post("maps/resources")
//		.then().assertThat().statusCode(200).body("scope",equalTo("app"))
//		.header("server", "apache").extract().response().asString();


        System.out.println(response);
        JsonPath js = new JsonPath(response); //for parsing Json
        String placeId = js.getString("place_id");

        System.out.println(placeId);

        //Update Place
        String newAddress = "Summer Walk, Africa";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\r\n" +
                        "\"place_id\":\"" + placeId + "\",\r\n" +
                        "\"address\":\"" + newAddress + "\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}").
                when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //Get Place

        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).extract().response().asString();
        JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, "Pacific ocean");
        //Cucumber Junit, Testng

        //Auth related code
        given().auth().oauth2(sessionId);

        //given().auth().oauth(placeId, newAddress, getPlaceResponse, actualAddress);
//
//		Note as well that the generic URL for getting a country is https://restcountries.com/v2/name/{country} where {country} is the country name.
//
//		Then, there are as well multiple ways to transfers path parameters.
//
//				Here are few examples
//
//		Example using pathParam():
//
//      Here the key name 'country' must match the url parameter {country}

        RestAssured.baseURI = "https://restcountries.com/";
        Response resp = RestAssured.given()
                .pathParam("country", "Finland")
                .when()
                .get("/name/{country}")
                .then()
                .assertThat().statusCode(200)
                .body("capital", equalTo("Helsinki")).extract().response();


    }

}

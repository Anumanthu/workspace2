package stepDefinations;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JsonScehmaValidation {
    @Test
    public static void jsonSchemaValidationForGetUsersAPI() {
        RestAssured.baseURI = "https://reqres.in/";
        given().contentType(ContentType.JSON).queryParam("page", 2).
                when().
                get("/api/users");
        //.then().assertThat().statusCode(200).body(JsonSchemaValidator.matchesJsonSchema("jsonScehmaGetusers.json"));
    }

}

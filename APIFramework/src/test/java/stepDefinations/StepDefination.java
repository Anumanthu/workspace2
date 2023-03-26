package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class StepDefination extends Utils {
    static String place_id;
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data = new TestDataBuild();

    @Given("^Add Place Payload with  \"([^\"]*\"([^\"]*)\")\" \"([^\"]*)\"")
    public void add_Place_Payload_with(String name, String language, String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        res = given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));


    }

    @When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request")
    public void user_calls_with_http_request(String resource, String method) {
        // Write code here that turns the phrase above into concrete actions
        // constructor will be called with value of resource which you pass
        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());


        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (method.equalsIgnoreCase("POST"))
            response = res.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = res.when().get(resourceAPI.getResource());

        //  To validate/assert response use ResponseSpecification class/library

        response.then().spec(resspec).extract().response().asString();

    }

    @Then("^the API call got success with status code \"([^\"]*)\"")
    public void the_API_call_got_success_with_status_code(String int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(), 200);


    }

    @Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"")
    public void in_response_body_is(String keyValue, String Expectedvalue) {
        // Write code here that turns the phrase above into concrete actions

        assertEquals(getJsonPath(response, keyValue), Expectedvalue);
    }

    @Then("^verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"")
    public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {

        // requestSpec
        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        assertEquals(actualName, expectedName);
    }

    @Given("^DeletePlace Payload")
    public void deleteplace_Payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions

        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));

    }

}

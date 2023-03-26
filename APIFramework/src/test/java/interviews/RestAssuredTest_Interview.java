package interviews;

//import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;


public class RestAssuredTest_Interview {

    public static void main(String args[]) {

        // Write test cases
        // https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=560102&date=30-09-2021

        // 1.pincode valid or not? request and response pin code should be same

        // RestAssured.baseURI = "";

        // System.out.println("Validating pincode from response
        // "+assertPincodeFromResponse("560102","30-09-2021"));
        System.out.println("Validating pincode " + validatePin("120300"));
    }

    public static boolean assertPincodeFromResponse(String pincode, String date) {

        //check given pincode is correct from the response status
        RestAssured.baseURI = "https://cdn-api.co-vin.in";

        //basic auth example
        //given().auth().preemptive().basic("username","pwd")
        //given().auth().basic("username","pwd")

        String response = given().queryParam("pincode", pincode).queryParam("date", date)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v2/appointment/sessions/public/calendarByPin")
                .then().
                assertThat().statusCode(200).extract()
                .response().asString();

        System.out.println(" Response is " + response);
        JsonPath js = new JsonPath(response);

        String pincodeActual = js.get("centers[0].pincode").toString();

        if (pincode.equalsIgnoreCase(pincodeActual)) {
            Assert.assertTrue(true);
            return true;
        } else {
            Assert.fail();
        }

        return true;
    }

    public static boolean validatePin(String pin) {
        // It can be only six digits.
        // It should not start with zero.
        // First digit of the pin code must be from 1 to 9.//[1-9]
        // Next five digits of the pin code may range from 0 to 9.

//        ^                         Start anchor
//        (?=.*[A-Z].*[A-Z])        Ensure string has two uppercase letters.
//        (?=.*[!@#$&*])            Ensure string has one special case letter.
//        (?=.*[0-9].*[0-9])        Ensure string has two digits.
//        (?=.*[a-z].*[a-z].*[a-z]) Ensure string has three lowercase letters.
//        .{8}                      Ensure string is of length 8.
//        $                         End anchor.

        // String pattern="(?=[1-9])(?=.*[0-9]).{6}";
        // String pattern = "^(?=[1-9])(?=\\d).{6}$";
        // String pattern="(?=[1-9])(?=[0-9]+).{6}";
        String pattern = "^(?=[1-9][0-9].*).{6}$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(pin);

        if (m.matches())
            return true;
        else
            return false;
        // 1st case

//        if(pin.length()!=6)
//        {
//            return false;
//        }
//        if(pin.charAt(0)=='0')
//            return false;
//
//        //int d=Integer.parseInt(String.valueOf(pin.charAt(0)));
//       for(int i=0;i<pin.length();i++)
//       {
//           //int d=-1;
//           int d=Integer.parseInt(String.valueOf(pin.charAt(0)));// - 0 9 12
//           if(d<0)
//           {
//              return false;
//           }
//           if(d>9)
//               return false;
//       }
//       return true;

    }
}

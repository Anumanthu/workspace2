package src.main.java.JsonPathLearning;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class TestPractice {


    private String username;
    private String pwd;
    loginCredentilas loginCredentilas = new loginCredentilas(username, pwd);

    // Headers : Authentication : Bearer xyx
    private String authType;

    TestPractice(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;

    }


    //{​​"username":"xyz" ,"pwd":"test"}​​

    @Test(dataProvider = "inputData")
    public static void loginToapp(String username, String pwd) {

        RestAssured.baseURI = "https://www.copart.com";

        String response = given().log().all().header("", "")
                .body("")
                .when().post("/api/login")
                .then().extract().response().toString();


        JsonPath js = new JsonPath(response.toString());

        js.getString("id");
        js.getString("");


    }


    public JsonObject getHeaderinfo(HashMap<String, String> hm) {
        JsonObject jsonObject = new JsonObject();
        Set<String> keySet = hm.keySet();

        for (String set : keySet) {
            jsonObject.addProperty(set, hm.get(keySet));
        }

        return jsonObject;

    }

    @DataProvider
    public Object[][] inputData() {
        Object[][] obj = new Object[3][2];


        obj[0][0] = "xyz";
        obj[0][1] = "test";

        obj[1][0] = "xyz1";
        obj[1][1] = "test1";

        obj[2][0] = "xyz2";
        obj[2][1] = "test2";

        return obj;

    }

}

class loginCredentilas {

    private String username;
    private String pwd;

    loginCredentilas(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

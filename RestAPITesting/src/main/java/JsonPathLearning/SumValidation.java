package src.main.java.JsonPathLearning;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import src.main.java.JsonPathLearning.files.payload;

public class SumValidation {

    @Test
    public void sumOfCourses() {
        JsonPath jsonPath = new JsonPath(payload.CoursePrice());

        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");

        System.out.println("Purchase Amount is " + purchaseAmount);

        int courseCount = jsonPath.getInt("courses.size()");
        System.out.println("Courses count is " + courseCount);
        int sumofAllcourses = 0;

        for (int i = 0; i < courseCount; i++) {
            System.out.println(jsonPath.get("courses[" + i + "].price").toString());

            sumofAllcourses = sumofAllcourses + jsonPath.getInt("courses[" + i + "].price") * jsonPath.getInt("courses[" + i + "].copies");
        }

        System.out.println("Sum of all cources is  " + sumofAllcourses);
        Assert.assertEquals(purchaseAmount, sumofAllcourses, "Total courses price is Not matching");
    }
}

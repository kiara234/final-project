package tests.API;

import base.ApiBaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VerifyLoginTest   extends ApiBaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"lizi@gmail.com", "lizi2005"}
        };
    }

    @Test(dataProvider = "loginData")
    public void verifyUserLogin(String email, String password) {

        Response response = RestAssured
                .given()
                .log().all()
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post("/api/verifyLogin")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        String rawBody = response.asString();
        String jsonOnly = rawBody.replaceAll("<.*?>", "");
        JsonPath jsonPath = new JsonPath(jsonOnly);

        int responseCode = jsonPath.getInt("responseCode");
        String message = jsonPath.getString("message");

        Assert.assertEquals(responseCode, 200, "Response code should be 200");
        Assert.assertEquals(message, "User exists!", "Login message should confirm user exists.");
    }
}

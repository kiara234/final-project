package tests.API;

import base.ApiBaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class VerifyLoginNegativeTest  extends ApiBaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test
    public void verifyLoginMissingEmail() {

        Response response =
                given()
                        .log().all()
                        .formParam("password", "password123")
                        .when()
                        .post("/api/verifyLogin")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        String body = response.getBody().asString();
        body = body.replaceAll("(?s)<.*?>", "");

        JsonPath jsonPath = new JsonPath(body);
        int responseCode = jsonPath.getInt("responseCode");
        String message = jsonPath.getString("message");

        Assert.assertEquals(responseCode, 400, "API responseCode should be 400 for missing parameters");
        Assert.assertEquals(message, "Bad request, email or password parameter is missing in POST request.",
                "Error message should indicate missing parameter");
    }
}

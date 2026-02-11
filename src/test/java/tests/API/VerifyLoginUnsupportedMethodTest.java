package tests.API;

import base.ApiBaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyLoginUnsupportedMethodTest extends ApiBaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test
    public void verifyLoginDeleteMethodNotAllowed() {

        Response response =
                given()
                        .log().all()
                        .when()
                        .delete("/api/verifyLogin")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        Assert.assertEquals(response.getStatusCode(), 200, "HTTP status is actually 200");

        int responseCode = response.jsonPath().getInt("responseCode");
        String message = response.jsonPath().getString("message");

        Assert.assertEquals(responseCode, 405, "responseCode should be 405");
        Assert.assertEquals(message, "This request method is not supported.", "Message should indicate method not supported");
    }
}

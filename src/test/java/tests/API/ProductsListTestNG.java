package tests.API;

import base.ApiBaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductsListTestNG  extends ApiBaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test
    public void verifyResponseCodeInBodyIs405() {

        Response response =
                RestAssured
                        .given()
                        .header("Content-Type", "application/json")
                        .when()
                        .post("/api/productsList");

        System.out.println("Response Body: " + response.asString());

        int responseCode = response.jsonPath().getInt("responseCode");
        String message = response.jsonPath().getString("message");


        Assert.assertEquals(message, "This request method is not supported.", "Message mismatch");
    }
}

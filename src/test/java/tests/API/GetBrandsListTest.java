package tests.API;

import base.ApiBaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetBrandsListTest  extends ApiBaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test
    public void verifyGetAllBrandsList() {

        Response response =
                given()
                        .log().all()
                        .when()
                        .get("/api/brandsList")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("brands", notNullValue())
                        .body("brands.size()", greaterThan(0))
                        .body("brands[0].id", notNullValue())
                        .body("brands[0].brand", notNullValue())
                        .time(lessThan(5000L))
                        .extract()
                        .response();

        int brandCount = response.jsonPath().getList("brands").size();
        Assert.assertTrue(brandCount > 0, "Brands list should not be empty");

        response.prettyPrint();
    }
}

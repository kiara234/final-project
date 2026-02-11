package tests.API;

import base.ApiBaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetProductsListTest  extends ApiBaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test
    public void verifyGetAllProductsList() {

        Response response =
                given()
                        .log().all()
                        .when()
                        .get("/api/productsList")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("products", notNullValue())
                        .body("products.size()", greaterThan(0))
                        .body("products[0].id", notNullValue())
                        .body("products[0].name", notNullValue())
                        .body("products[0].price", notNullValue())
                        .time(lessThan(5000L))
                        .extract()
                        .response();

        int productCount = response.jsonPath().getList("products").size();
        Assert.assertTrue(productCount > 0, "Products list should not be empty");

        response.prettyPrint();


    }
}

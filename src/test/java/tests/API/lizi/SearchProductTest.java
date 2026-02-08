package tests.API.lizi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SearchProductTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @DataProvider(name = "searchKeywords")
    public Object[][] searchKeywords() {
        return new Object[][] {
                {"top"},
                {"tshirt"},
                {"jean"}
        };
    }

    @Test(dataProvider = "searchKeywords")
    public void verifySearchProduct(String keyword) {

        Response response =
                given()
                        .log().all()
                        .formParam("search_product", keyword)
                        .when()
                        .post("/api/searchProduct")
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
        Assert.assertTrue(productCount > 0, "Search should return at least 1 product for: " + keyword);

        response.prettyPrint();
    }
}

package tests.API;

import base.ApiBaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class SearchProductNegativeTest   extends ApiBaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    @Test
    public void verifySearchProductWithoutParam() {

        String response = RestAssured
                .given()
                .log().all()
                .when()
                .post("/api/searchProduct")
                .then()
                .log().all()
                .statusCode(200)
                .extract().asString();

        String jsonOnly = response.replaceAll("<.*?>", "");
        JsonPath jsonPath = new JsonPath(jsonOnly);

        int responseCode = jsonPath.getInt("responseCode");
        String message = jsonPath.getString("message");

        assertThat(responseCode, equalTo(400));
        assertThat(message, containsString("search_product parameter is missing"));
    }
}

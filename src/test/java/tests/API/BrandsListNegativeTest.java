package tests.API;

import base.ApiBaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class BrandsListNegativeTest extends ApiBaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://automationexercise.com";
    }

    private JsonPath extractJsonFromHtml(Response response) {
        String body = response.getBody().asString();
        String json = body.replaceAll("(?s)<.*?>", "");
        return new JsonPath(json);
    }

    @Test
    public void verifyPutMethodNotAllowed() {
        Response response =
                given()
                        .log().all()
                        .when()
                        .put("/api/brandsList")
                        .then()
                        .log().all()
                        .extract().response();

        JsonPath json = extractJsonFromHtml(response);
        int responseCode = json.getInt("responseCode");
        String message = json.getString("message");

        assertThat(responseCode, is(405));
        assertThat(message, containsString("This request method is not supported"));
    }
}

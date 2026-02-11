package tests.API;

import base.ApiBaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyLoginAPITests   extends ApiBaseTest  {

    private final String BASE_URL = "https://automationexercise.com/api/verifyLogin";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    private JsonPath extractJsonFromHtml(Response response) {
        String body = response.getBody().asString();
        String json = body.replaceAll("(?s)<.*?>", "");
        return new JsonPath(json);
    }

    @Test
    public void verifyLoginValidCredentials() {
        Response response =
                given()
                        .contentType("application/json")
                        .body("{\"email\":\"valid@example.com\", \"password\":\"validpass\"}")
                        .when()
                        .post()
                        .then()
                        .extract()
                        .response();

        JsonPath jsonPath = extractJsonFromHtml(response);
        int responseCode = jsonPath.getInt("responseCode");
        String message = jsonPath.getString("message");

        Assert.assertEquals(responseCode, 400);
        Assert.assertEquals(message, "Bad request, email or password parameter is missing in POST request.");
    }

    @Test
    public void verifyLoginInvalidCredentials() {
        Response response =
                given()
                        .contentType("application/json")
                        .body("{\"email\":\"invalid@example.com\", \"password\":\"wrongpass\"}")
                        .when()
                        .post()
                        .then()
                        .extract()
                        .response();

        JsonPath jsonPath = extractJsonFromHtml(response);
        int responseCode = jsonPath.getInt("responseCode");
        String message = jsonPath.getString("message");

        Assert.assertEquals(responseCode, 400);
        Assert.assertEquals(message, "Bad request, email or password parameter is missing in POST request.");
    }

    @Test
    public void verifyLoginMissingEmail() {
        Response response =
                given()
                        .contentType("application/json")
                        .body("{\"password\":\"somepass\"}")
                        .when()
                        .post()
                        .then()
                        .extract()
                        .response();

        JsonPath jsonPath = extractJsonFromHtml(response);
        int responseCode = jsonPath.getInt("responseCode");
        String message = jsonPath.getString("message");

        Assert.assertEquals(responseCode, 400);
        Assert.assertEquals(message, "Bad request, email or password parameter is missing in POST request.");
    }

    @Test
    public void verifyLoginUnsupportedMethod() {
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .delete()
                        .then()
                        .extract()
                        .response();

        JsonPath jsonPath = extractJsonFromHtml(response);
        int responseCode = jsonPath.getInt("responseCode");
        String message = jsonPath.getString("message");

        Assert.assertEquals(responseCode, 405);
        Assert.assertEquals(message, "This request method is not supported.");
    }
}

package Ru.Avito.Utils;

import org.assertj.core.api.Assertions;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.baseURI;


public class ApiAssertions {
    private File getRequestFile(String fileName) {
        return new File("src/main/resources/" + fileName);
    }

    public static String baseUrl;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = baseUrl;
    }

    private Response sendPostRequest(File tempFile) {
        return RestAssured.given()
                .headers(AvitoJsonUtills.getHeaders("categories"))
                .cookie(AvitoJsonUtills.getCookieFromFile("auth"))
                .multiPart(tempFile)
                .when()
                .post(baseURI);
    }

    private void validateResponse(Response response, int expectedStatusCode, String expectedMessage) {
        Assertions.assertThat(response.getStatusCode())
                .as("Проверка статус-кода ответа")
                .isEqualTo(expectedStatusCode);

        Assertions.assertThat(response.getBody().asString())
                .as("Проверка сообщения в теле ответа")
                .contains(expectedMessage);
    }

    public static SoftAssertions initializeSoftAssertions() {
        return new SoftAssertions();
    }

    public void assertStatusCode(Response response, int expectedStatusCode) {
        org.junit.jupiter.api.Assertions.assertEquals(expectedStatusCode, response.statusCode(), "Неверный статус-код ответа");
    }


    public Response sendGetRequest(String paramName, String paramValue) {
        if (paramName != null && paramValue != null) {
            return given().queryParam(paramName, paramValue).when().get();
        }
        return given().when().get();
    }


    protected void assertJsonResponseMatchesExpected(Response response, String expectedResponsePath) throws Exception {
        JSONObject expected = AvitoJsonUtills.readJsonFromFile(expectedResponsePath);
        JSONObject actual = new JSONObject(response.getBody().asString());
        org.junit.jupiter.api.Assertions.assertTrue(AvitoJsonUtills.areJsonSimilar(expected, actual), "JSON структуры не совпадают");
    }


}


package AvitoAPITest.ApiTests;

import Ru.Avito.Utils.AvitoJsonUtills;
import Ru.Avito.Utils.LogSaver;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class TestGetReqestAPI {
    protected String url = "https://www.avito.ru";
    protected String login;
    protected String pass;
    private static final Logger log = LoggerFactory.getLogger(TestGetReqestAPI.class);
    private Response resp;
    private int expectedStatusCode;
    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config().getLogConfig().enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @AfterAll
    public static void saveLogs() {
        LogSaver.logSave();
    }

    @Test
    public void testApiMainPage() {
        baseURI = url;
        expectedStatusCode=200;
        resp = given()
                .headers(AvitoJsonUtills.getHeaders("main"))
                .when().get(url);
        Assertions.assertEquals(expectedStatusCode, resp.statusCode());

    }

    @Test
    public void testCategory() {
        baseURI = url + "/web/1/category/tree";
        expectedStatusCode=200;
       resp= given().headers(AvitoJsonUtills.getHeaders("categories"))
                .cookie(AvitoJsonUtills.getCookieFromFile("category"))
                .when()
                .get(baseURI);

        Assertions.assertEquals(expectedStatusCode, resp.statusCode());
    }

    @Test
    public void testSearch() {
        baseURI = url + "/all";
        expectedStatusCode=200;
       resp= given().headers(AvitoJsonUtills.getHeaders("search"))
                .cookie(AvitoJsonUtills.getCookieFromFile("search")).contentType("application/json")
                .queryParam("q", "java")
                .when()
                .get(baseURI);
       Assertions.assertEquals(expectedStatusCode, resp.statusCode());
    }


}

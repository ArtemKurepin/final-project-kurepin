package AvitoAPITest.ApiTests;

import Ru.Avito.Utils.AvitoJsonUtills;
import Ru.Avito.Utils.LogSaver;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // Или детальное логирование:
        RestAssured.config().getLogConfig().enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterAll
    public static void saveLogs() {
        LogSaver.logSave();
    }

    @Test
    public void testApiMainPage() {
       baseURI = url;
        given()
                .headers(AvitoJsonUtills.getHeaders("main")).then()
                .statusCode(200)
                .body(containsString("Avito"))
                .log().headers();
    }

    @Test
    public void testCategory() {
        baseURI = url + "/web/1/category/tree";
        given().headers(AvitoJsonUtills.getHeaders("categories"))
                .cookie(AvitoJsonUtills.getCookieFromFile("category"))
                .when()
                .get(baseURI)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
    }

    @Test
    public void testSearch() {
       baseURI = url + "/all";
        given().headers(AvitoJsonUtills.getHeaders("search"))
                .cookie(AvitoJsonUtills.getCookieFromFile("search")).contentType("application/json")
                .queryParam("q", "java")
                .when()
                .get(baseURI)
                .then()
                .statusCode(200).log()
                .all();
    }


}

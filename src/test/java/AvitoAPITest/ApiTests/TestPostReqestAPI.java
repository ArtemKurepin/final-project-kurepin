package AvitoAPITest.ApiTests;

import Ru.Avito.Utils.AvitoJsonUtills;
import Ru.Avito.Utils.LogSaver;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
import java.util.Date;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestPostReqestAPI {
    private static final Logger log = LoggerFactory.getLogger(TestPostReqestAPI.class);

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        // Или детальное логирование:
        RestAssured.config().getLogConfig().enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @Test
    public void testNegativeAuth() {
        baseURI = "https://www.avito.ru/web/1/auth";
        File tempFile = new File("src/main/resources/LoginFormParams.txt");
        given().headers(AvitoJsonUtills.getHeaders("categories"))
                .cookie(AvitoJsonUtills.getCookieFromFile("auth"))
                .multiPart(tempFile)
                .when()
                .post(baseURI)
                .then()
                .log()
                .all();
    }

    @Test
    public void testNegativeAuthWithoutPass() {
        baseURI = "https://www.avito.ru/web/1/auth";
        File tempFile = new File("src/main/resources/LoginFormParamsWithoutPass.txt");
        given().headers(AvitoJsonUtills.getHeaders("categories"))
                .cookie(AvitoJsonUtills.getCookieFromFile("auth"))
                .multiPart(tempFile)
                .when()
                .post(baseURI)
                .then()
                .log()
                .all();
    }

    @Test
    public void testNegativeAuthWithoutLogin() {
        baseURI = "https://www.avito.ru/web/1/auth";
        File tempFile = new File("src/main/resources/LoginFormParamsWithoutLogin.txt");
        given().headers(AvitoJsonUtills.getHeaders("categories"))
                .cookie(AvitoJsonUtills.getCookieFromFile("auth"))
                .multiPart(tempFile)
                .when()
                .post(baseURI)
                .then()
                .log()
                .all();
    }

    @AfterAll
    public static void saveLogs(){
        LogSaver.logSave();
    }
}

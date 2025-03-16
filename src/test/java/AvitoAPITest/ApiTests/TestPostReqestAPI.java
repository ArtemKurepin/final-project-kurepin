package AvitoAPITest.ApiTests;

import Ru.Avito.Utils.ApiAssertions;
import Ru.Avito.Utils.AvitoJsonUtills;
import Ru.Avito.Utils.AvitoSoftlyAssert;
import Ru.Avito.Utils.LogSaver;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestPostReqestAPI extends ApiAssertions {
    private static final Logger log = LoggerFactory.getLogger(TestPostReqestAPI.class);

    private static final String EXPECTED_JSON_RESPONSE_PATH = "src/main/resources/ExpectedLoginResult.json";

    File tempFile;
    RequestSpecification requestSpecification;
    SoftAssertions sf;
    AvitoSoftlyAssert softlyAssert;

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.config().getLogConfig().enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeEach
    public void beforeEach() {
        sf = ApiAssertions.initializeSoftAssertions();
        baseURI = "https://www.avito.ru/web/1/auth";
        requestSpecification = given().headers(AvitoJsonUtills.getHeaders("categories"))
                .cookie(AvitoJsonUtills.getCookieFromFile("auth"));
        softlyAssert = new AvitoSoftlyAssert();
    }

    @Test
    public void testNegativeAuth() {

        tempFile = new File("src/main/resources/LoginFormParams.txt");
        Response response = requestSpecification
                .multiPart(tempFile)
                .when()
                .post(baseURI);
        softlyAssert.assertStatusCode(sf, response, 200);
        softlyAssert.assertThatJson(sf, response, EXPECTED_JSON_RESPONSE_PATH);
        softlyAssert.assertAll(sf);

    }

    @Test
    public void testNegativeAuthWithoutPass() {
        File tempFile = new File("src/main/resources/LoginFormParamsWithoutPass.txt");
        Response response = requestSpecification
                .multiPart(tempFile)
                .when()
                .post(baseURI);
        softlyAssert.assertStatusCode(sf, response, 200);
        softlyAssert.assertThatJson(sf, response, EXPECTED_JSON_RESPONSE_PATH);
        softlyAssert.assertAll(sf);

    }

    @Test
    public void testNegativeAuthWithoutLogin() {
        baseURI = "https://www.avito.ru/web/1/auth";
        File tempFile = new File("src/main/resources/LoginFormParamsWithoutLogin.txt");
        Response response = requestSpecification
                .multiPart(tempFile)
                .when()
                .post(baseURI);
        softlyAssert.assertStatusCode(sf, response, 200);
        softlyAssert.assertThatJson(sf, response, EXPECTED_JSON_RESPONSE_PATH);
        softlyAssert.assertAll(sf);


    }

    @AfterAll
    public static void saveLogs() {
        LogSaver.logSave();
    }
}

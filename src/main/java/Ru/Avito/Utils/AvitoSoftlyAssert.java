package Ru.Avito.Utils;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

public class AvitoSoftlyAssert extends ApiAssertions {
    public void assertStatusCode(SoftAssertions softly, Response response, int statusCode) {
        softly.assertThat(response.getStatusCode())
                .as("Проверка статус-кода")
                .isEqualTo(statusCode);
    }

    public void assertThatJson(SoftAssertions softly, Response response,String path) {
        softly.assertThatCode(() -> assertJsonResponseMatchesExpected(response, path))
                .as("Проверка JSON ответа")
                .doesNotThrowAnyException();
    }

    public void assertAll(SoftAssertions softly) {
        softly.assertAll();
    }

}

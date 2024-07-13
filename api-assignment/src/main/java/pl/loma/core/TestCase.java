package pl.loma.core;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.config.LogConfig.logConfig;

public abstract class TestCase {

    protected static final String BASE_URL = "https://swapi.dev/api/";

    protected final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .setConfig(createConfig())
            .build();

    private RestAssuredConfig createConfig() {
        return RestAssuredConfig.newConfig().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
    }

}

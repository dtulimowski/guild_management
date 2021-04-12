package io.github.guild.application;

import io.restassured.RestAssured;
import io.restassured.config.MatcherConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.config.MatcherConfig.ErrorDescriptionType.HAMCREST;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void beforeEach() {
        RestAssured.baseURI = String.format("http://localhost:%d", port);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.config = RestAssured.config().matcherConfig(new MatcherConfig(HAMCREST));
    }
}

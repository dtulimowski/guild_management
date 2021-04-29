package io.github.guild.application.controller.guild;

import io.github.guild.application.controller.guild.request.CreateGuildTestRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@Service
public class GuildTestApi {

    public Response createGuild(CreateGuildTestRequest createGuildRequest, HttpStatus expectedStatus) {
        return given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .body(createGuildRequest)
                .when()
                .post("/guilds")
                .then()
                .statusCode(expectedStatus.value())
                .extract()
                .response();
    }

    public Response deleteGuild(String uuid, HttpStatus expectedStatus) {
        return given()
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .pathParam("uuid", uuid)
                .when()
                .delete("/guilds/{uuid}")
                .then()
                .statusCode(expectedStatus.value())
                .extract()
                .response();
    }
}

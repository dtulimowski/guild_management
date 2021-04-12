package io.github.guild.application.controller.guild;

import io.github.guild.application.IntegrationTest;
import io.github.guild.application.controller.guild.request.CreateGuildTestRequest;
import io.github.guild.application.helper.ErrorResponse;
import io.github.guild.domain.valueobject.GuildAccessType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

class CreateGuildTests extends IntegrationTest {

    @Autowired
    private GuildTestApi guildTestApi;

    @Autowired
    private GuildTestApiResponseMapper guildTestApiResponseMapper;

    @ParameterizedTest
    @EnumSource(GuildAccessType.class)
    void createGuildWithoutAssignees(GuildAccessType guildAccessType) {
        guildTestApi.createGuild(CreateGuildTestRequest.builder()
                .name("Greedy Parasites")
                .accessType(guildAccessType.getAccessType())
                .language("pl")
                .build(), HttpStatus.CREATED);
    }

    @ParameterizedTest
    @EnumSource(GuildAccessType.class)
    void createGuildWithAssignees(GuildAccessType guildAccessType) {
        var assignees = List.of("John Doe", "Mary Doe");

        guildTestApi.createGuild(CreateGuildTestRequest.builder()
                .name("Greedy Parasites")
                .accessType(guildAccessType.getAccessType())
                .language("pl")
                .assignees(assignees)
                .build(), HttpStatus.CREATED);
    }

    @Test
    void tryToCreateGuildWithoutName() {
        Response response = guildTestApi.createGuild(CreateGuildTestRequest.builder()
                .name(null)
                .accessType(GuildAccessType.PRIVATE.getAccessType())
                .language("pl")
                .build(), HttpStatus.BAD_REQUEST);

        ErrorResponse errorResponse = guildTestApiResponseMapper.mapToError(response);
        Assertions.assertEquals("must not be null", errorResponse.getErrors().getName());
    }

    @Test
    void tryToCreateGuildWithInvalidAccessType() {
        Response response = guildTestApi.createGuild(CreateGuildTestRequest.builder()
                .name("Greedy Parasites")
                .accessType("invalid access type")
                .language("pl")
                .build(), HttpStatus.BAD_REQUEST);

        ErrorResponse errorResponse = guildTestApiResponseMapper.mapToError(response);
        Assertions.assertTrue(errorResponse.getMessage().contains("invalid enum value specified"));
    }
}

package io.github.guild.application.controller.guild;

import io.github.guild.application.IntegrationTest;
import io.github.guild.application.controller.guild.request.CreateGuildTestRequest;
import io.github.guild.application.controller.guild.request.DeleteGuildTestRequest;
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
import java.util.UUID;

class DeleteGuildTests extends IntegrationTest {
/*
    @Autowired
    private GuildTestApi guildTestApi;

    @Autowired
    private GuildTestApiResponseMapper guildTestApiResponseMapper;

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
    }*/

    @Test
    void deleteGuild() {
       /* guildTestApi.deleteGuild(DeleteGuildTestRequest.builder()
                .guildId()
                .build(), HttpStatus.NO_CONTENT);*/
    }


}

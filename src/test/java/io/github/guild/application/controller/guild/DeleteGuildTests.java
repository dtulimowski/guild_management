package io.github.guild.application.controller.guild;

import io.github.guild.application.IntegrationTest;
import io.github.guild.application.helper.ErrorResponse;
import io.github.guild.domain.entity.Assignee;
import io.github.guild.domain.entity.Guild;
import io.github.guild.domain.repository.assignee.InMemoryAssigneeRepository;
import io.github.guild.domain.repository.guild.InMemoryGuildRepository;
import io.github.guild.domain.valueobject.GuildAccessType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.UUID;

class DeleteGuildTests extends IntegrationTest {

    @Autowired
    private GuildTestApi guildTestApi;

    @Autowired
    private InMemoryGuildRepository inMemoryGuildRepository;

    @Autowired
    private InMemoryAssigneeRepository inMemoryAssigneeRepository;

    @Autowired
    private GuildTestApiResponseMapper guildTestApiResponseMapper;

    public Guild createGuild(GuildAccessType guildAccessType) {
        Guild guild = new Guild();
        guild.setId(12345L);
        guild.setName("Greedy Parasites");
        guild.setGuildAccessType(guildAccessType);

        inMemoryGuildRepository.create(guild);
        inMemoryAssigneeRepository.add(new Assignee("123"), guild);

        return guild;
    }

    @ParameterizedTest(name = "I delete a guild with access: {0}")
    @EnumSource(GuildAccessType.class)
    void deleteGuildWithAccess(GuildAccessType guildAccessType) {

        Guild guild = createGuild(guildAccessType);
        guildTestApi.deleteGuild(guild.getGuildId().toString(), HttpStatus.NO_CONTENT);
    }

    @ParameterizedTest(name = "I delete a guild with non-existent UUID and access: {0}")
    @EnumSource(GuildAccessType.class)
    void deleteGuildWithNonExistentUUID(GuildAccessType guildAccessType) {

        createGuild(guildAccessType);
        Response response = guildTestApi.deleteGuild(UUID.randomUUID().toString(), HttpStatus.NOT_FOUND);

        ErrorResponse errorResponse = guildTestApiResponseMapper.mapToError(response);
        Assertions.assertTrue(errorResponse.getMessage().contains("Item not found"));
    }

    @ParameterizedTest(name = "I delete a guild with incorrect UUID and access: {0}")
    @EnumSource(GuildAccessType.class)
    void deleteGuildWithIncorrectUUID(GuildAccessType guildAccessType) {

        createGuild(guildAccessType);
        guildTestApi.deleteGuild("123", HttpStatus.BAD_REQUEST);
    }

    @ParameterizedTest(name = "I verify if guild has been deleted and access: {0}")
    @EnumSource(GuildAccessType.class)
    void verifyIfGuildHasBeenDeleted(GuildAccessType guildAccessType) {

        Guild guild = createGuild(guildAccessType);
        guildTestApi.deleteGuild(guild.getGuildId().toString(), HttpStatus.NO_CONTENT);
        guildTestApi.deleteGuild(guild.getGuildId().toString(), HttpStatus.NOT_FOUND);
    }
}

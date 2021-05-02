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

import java.util.Locale;
import java.util.UUID;

class GetGuildTests extends IntegrationTest {

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
        guild.setLocale(Locale.ENGLISH);
        guild.setGuildAccessType(guildAccessType);

        inMemoryGuildRepository.create(guild);
        inMemoryAssigneeRepository.add(new Assignee("123"), guild);

        return guild;
    }

    @ParameterizedTest(name = "I get a guild with access: {0}")
    @EnumSource(GuildAccessType.class)
    void getGuildWithAccess(GuildAccessType guildAccessType) {

        Guild guild = createGuild(guildAccessType);
        Response response = guildTestApi.getGuildByUUID(guild.getGuildId().toString(), HttpStatus.OK);

        Assertions.assertAll(
                () -> Assertions.assertEquals(guild.getGuildId().toString(), response.jsonPath().get("guildview.guildId")),
                () -> Assertions.assertEquals("Greedy Parasites", response.jsonPath().get("guildview.name")),
                () -> Assertions.assertEquals("en", response.jsonPath().get("guildview.locale")),
                () -> Assertions.assertEquals(guildAccessType.getAccessType().toUpperCase(), response.jsonPath().get("guildview.guildAccessType"))
        );
    }

    @ParameterizedTest(name = "I get a guild with non-existent UUID and access: {0}")
    @EnumSource(GuildAccessType.class)
    void getGuildWithNonExistentUUID(GuildAccessType guildAccessType) {

        createGuild(guildAccessType);
        Response response = guildTestApi.getGuildByUUID(UUID.randomUUID().toString(), HttpStatus.NOT_FOUND);

        ErrorResponse errorResponse = guildTestApiResponseMapper.mapToError(response);
        Assertions.assertTrue(errorResponse.getErrorDetail().contains("Guild not found"));
    }

    @ParameterizedTest(name = "I get a guild with incorrect UUID and access: {0}")
    @EnumSource(GuildAccessType.class)
    void deleteGuildWithIncorrectUUID(GuildAccessType guildAccessType) {

        createGuild(guildAccessType);
        guildTestApi.getGuildByUUID("123", HttpStatus.BAD_REQUEST);
    }
}

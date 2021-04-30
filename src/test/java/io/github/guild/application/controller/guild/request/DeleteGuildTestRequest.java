package io.github.guild.application.controller.guild.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class DeleteGuildTestRequest {
    private final UUID guildId;

}

package io.github.guild.application.controller.guild.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@ToString
@Getter
public class DeleteGuildRequest {

    @NotNull
    @NotEmpty
    @Schema(description = "Guild name to be removed")
    private UUID guildId;

}

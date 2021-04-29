package io.github.guild.application.controller.guild.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor
public class CreateGuildRequest {

    @NotNull
    @NotEmpty
    @Schema(description = "Name of the guild")
    private String name;

    @NotNull
    @Schema(description = "Specifies what's the guild's visibility. Public or Private")
    private GuildAccessTypeEnum accessType;

    @NotNull
    @NotEmpty
    @Schema(description = "Language of guild's members")
    private String language;

    @Schema(description = "Should contain the ID of users assigned during Guild creation")
    @Nullable
    private List<String> assignees;
}

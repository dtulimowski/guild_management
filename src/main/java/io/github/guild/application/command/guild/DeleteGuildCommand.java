package io.github.guild.application.command.guild;

import io.github.guild.application.command.Command;
import io.github.guild.domain.entity.Assignee;
import io.github.guild.domain.valueobject.GuildAccessType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class DeleteGuildCommand implements Command {
    private final UUID guildId;
}

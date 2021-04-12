package io.github.guild.application.command.guild;

import io.github.guild.application.command.Command;
import io.github.guild.domain.entity.Assignee;
import io.github.guild.domain.valueobject.GuildAccessType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@Getter
public class CreateGuildCommand implements Command {
    private final String name;
    private final GuildAccessType guildAccessType;
    private final Locale language;
    private final List<Assignee> assignees;
}

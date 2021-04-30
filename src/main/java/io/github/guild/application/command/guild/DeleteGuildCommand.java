package io.github.guild.application.command.guild;

import io.github.guild.application.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class DeleteGuildCommand implements Command {
    private final UUID guildId;
}

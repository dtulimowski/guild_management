package io.github.guild.application.handler;

import io.github.guild.application.command.CommandResult;
import io.github.guild.application.command.Command;

public interface CommandHandler<T extends Command> {
    CommandResult handle(T command);
}

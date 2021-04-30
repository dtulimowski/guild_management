package io.github.guild.application.handler.user;

import io.github.guild.application.command.CommandResult;
import io.github.guild.application.command.guild.DeleteGuildCommand;
import io.github.guild.application.handler.CommandHandler;
import io.github.guild.domain.entity.Guild;
import io.github.guild.domain.repository.assignee.AssigneeRepository;
import io.github.guild.domain.repository.guild.GuildRepository;
import io.github.guild.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteGuildCommandHandler implements CommandHandler<DeleteGuildCommand> {
    private final GuildRepository guildRepository;
    private final AssigneeRepository assigneeRepository;

    @Override
    public CommandResult handle(DeleteGuildCommand command) {
        Guild guild = guildRepository.get(command.getGuildId()).orElseThrow(
                () -> new NotFoundException("Guild not found"));
        guildRepository.delete(command.getGuildId());
        assigneeRepository.delete(guild.getId());

        return null;
    }
}

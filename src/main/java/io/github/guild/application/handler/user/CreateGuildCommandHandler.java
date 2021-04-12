package io.github.guild.application.handler.user;

import io.github.guild.application.command.CommandResult;
import io.github.guild.application.command.guild.CreateGuildCommand;
import io.github.guild.application.handler.CommandHandler;
import io.github.guild.domain.entity.Guild;
import io.github.guild.domain.repository.assignee.AssigneeRepository;
import io.github.guild.domain.repository.guild.GuildRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateGuildCommandHandler implements CommandHandler<CreateGuildCommand> {
    private final GuildRepository guildRepository;
    private final AssigneeRepository assigneeRepository;

    @Override
    public CommandResult handle(CreateGuildCommand command) {

        Guild guild = guildRepository.create(Guild.builder()
                .name(command.getName())
                .locale(command.getLanguage())
                .guildAccessType(command.getGuildAccessType())
                .build());

        command.getAssignees().forEach(assignee -> assigneeRepository.add(assignee, guild));

        return new CommandResult(guild.getGuildId());
    }
}

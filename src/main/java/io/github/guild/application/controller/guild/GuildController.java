package io.github.guild.application.controller.guild;

import io.github.guild.application.command.CommandResult;
import io.github.guild.application.command.guild.CreateGuildCommand;
import io.github.guild.application.command.guild.DeleteGuildCommand;
import io.github.guild.application.controller.guild.request.CreateGuildRequest;
import io.github.guild.application.controller.guild.request.GuildAccessTypeEnum;
import io.github.guild.application.controller.guild.response.CreateGuildResponse;
import io.github.guild.application.handler.CommandHandler;
import io.github.guild.domain.entity.Assignee;
import io.github.guild.domain.valueobject.GuildAccessType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Service
@AllArgsConstructor
public class GuildController implements GuildApi {
    private final CommandHandler<CreateGuildCommand> createGuildCommandHandler;
    private final CommandHandler<DeleteGuildCommand> deleteGuildCommandHandler;

    @Override
    public ResponseEntity<CreateGuildResponse> create(@Valid CreateGuildRequest createGuildRequest) {
        var addRoleCommand = new CreateGuildCommand(createGuildRequest.getName(),
                mapGuildAccessType(createGuildRequest.getAccessType()),
                new Locale(createGuildRequest.getLanguage()),
                mapAssignees(createGuildRequest.getAssignees()));

        CommandResult commandResult = createGuildCommandHandler.handle(addRoleCommand);
        return new ResponseEntity<>(new CreateGuildResponse(commandResult.getId()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> delete(UUID guildId) {
        var addRoleCommand = new DeleteGuildCommand(guildId);
        deleteGuildCommandHandler.handle(addRoleCommand);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private GuildAccessType mapGuildAccessType(GuildAccessTypeEnum guildAccessTypeEnum) {
        switch (guildAccessTypeEnum) {
            case PUBLIC: return GuildAccessType.PUBLIC;
            case PRIVATE: return GuildAccessType.PRIVATE;
        }

        throw new IllegalArgumentException("Invalid access type: " + guildAccessTypeEnum);
    }

    private List<Assignee> mapAssignees(List<String> assignees) {
        if (assignees == null) {
            return new ArrayList<>();
        }

        return assignees.stream()
                .map(Assignee::new)
                .collect(Collectors.toList());
    }
}

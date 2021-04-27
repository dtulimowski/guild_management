package io.github.guild.domain.repository.assignee;

import io.github.guild.domain.entity.Assignee;
import io.github.guild.domain.entity.Guild;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Repository
@Primary
public class InMemoryAssigneeRepository implements AssigneeRepository {
    private static final Map<Long, List<Assignee>> DATABASE = new HashMap<>();

    @Override
    public Assignee add(Assignee assignee, Guild guild) {
        Long guildId = guild.getId();

        List<Assignee> assignees = DATABASE.get(guildId);
        if (assignees == null) {
            assignees = new ArrayList<>();
        }

        assignees.add(assignee);
        DATABASE.put(guildId, assignees);

        return assignee;
    }

    @Override
    public void delete(@NonNull Long id) {
        DATABASE.remove(id);
    }
}

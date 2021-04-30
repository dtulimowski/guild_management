package io.github.guild.domain.repository.assignee;

import io.github.guild.domain.entity.Assignee;
import io.github.guild.domain.entity.Guild;

public interface AssigneeRepository {

    Assignee add(Assignee assignee, Guild guild);
    void delete(Long uuid);

}

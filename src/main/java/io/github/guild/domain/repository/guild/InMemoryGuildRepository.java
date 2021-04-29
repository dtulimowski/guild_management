package io.github.guild.domain.repository.guild;

import io.github.guild.domain.entity.Guild;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Repository
@Primary
public class InMemoryGuildRepository implements GuildRepository {
    private static final Map<Long, Guild> DATABASE = new HashMap<>();

    @Override
    public Guild create(Guild guild) {
        Long id = new Random().nextLong();
        guild.setId(id);
        guild.setGuildId(UUID.randomUUID());

        DATABASE.put(id, guild);

        return guild;
    }

    @Override
    public void delete(UUID guildId) {
        DATABASE.entrySet().removeIf(guild -> guild.getValue().getGuildId().equals(guildId));
    }

    @Override
    public Optional<Guild> get(UUID guildId) {
        return DATABASE.values().stream().filter(guild -> guild.getGuildId().equals(guildId)).findAny();
    }
}

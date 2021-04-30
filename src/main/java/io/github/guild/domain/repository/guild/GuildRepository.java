package io.github.guild.domain.repository.guild;

import io.github.guild.domain.entity.Guild;

import java.util.Optional;
import java.util.UUID;

public interface GuildRepository {

    Guild create(Guild guild);
    void delete(UUID guildId);
    Optional<Guild> get(UUID guildId);
}

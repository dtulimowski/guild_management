package io.github.guild.application.service.guild;

import io.github.guild.application.entity.GuildView;
import io.github.guild.domain.entity.Guild;
import io.github.guild.domain.repository.guild.GuildRepository;
import io.github.guild.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GuildService {
    @Autowired
    private GuildRepository guildRepository;

    public GuildView getGuildView(UUID guildID) {
        Guild guild = guildRepository.get(guildID).orElseThrow(
                () -> new NotFoundException("Guild not found"));
        return buildGuildView(guild);
    }

    private GuildView buildGuildView(Guild guild) {
        return GuildView.builder()
                .guildId(guild.getGuildId())
                .guildAccessType(guild.getGuildAccessType())
                .locale(guild.getLocale())
                .name(guild.getName())
                .build();
    }


}

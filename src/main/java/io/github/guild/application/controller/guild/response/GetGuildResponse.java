package io.github.guild.application.controller.guild.response;

import io.github.guild.application.entity.GuildView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetGuildResponse {
    private GuildView guildview;
}

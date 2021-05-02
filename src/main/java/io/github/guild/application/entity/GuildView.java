package io.github.guild.application.entity;

import io.github.guild.domain.valueobject.GuildAccessType;
import lombok.*;

import java.util.Locale;
import java.util.UUID;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuildView {
    private UUID guildId;
    private String name;
    private Locale locale;
    private GuildAccessType guildAccessType;
}
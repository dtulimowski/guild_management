package io.github.guild.domain.valueobject;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GuildAccessType {
    PUBLIC("public"),
    PRIVATE("private");

    private final String accessType;

    public String getAccessType() {
        return accessType;
    }
}

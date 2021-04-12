package io.github.guild.application.controller.guild.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GuildAccessTypeEnum {
    @JsonProperty("public")
    PUBLIC,
    @JsonProperty("private")
    PRIVATE;
}

package io.github.guild.application.controller.guild.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CreateGuildTestRequest {
    private final String name;
    private final String accessType;
    private final String language;
    private final List<String> assignees;
}

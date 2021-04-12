package io.github.guild.application.controller.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorDetail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Singular
    private Map<String, String> errors;
}

package io.github.guild.application.helper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorResponse {
    @JsonIgnore
    private String timestamp;
    private String message;
    private String errorDetail;
    private ErrorsEntity errors;
    private String path;
    private String error;
    private String status;

    @Getter
    public static class ErrorsEntity {
        private String name;
    }
}

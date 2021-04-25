package io.github.guild.application.controller.guild;

import io.github.guild.application.helper.ErrorResponse;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

@Service
public class GuildTestApiResponseMapper {

    ErrorResponse mapToError(Response response) {
        return response.as(ErrorResponse.class);
    }
}

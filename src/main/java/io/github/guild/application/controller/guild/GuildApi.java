package io.github.guild.application.controller.guild;

import io.github.guild.application.controller.guild.request.CreateGuildRequest;
import io.github.guild.application.controller.guild.response.CreateGuildResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/guilds")
@Tag(name = "Guild Management API")
public interface GuildApi {

    @Operation(description = "Create guild")
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ApiResponse(responseCode = "204", description = "Successfully created a guild")
    @ApiResponse(responseCode = "400", description = "Bad request")
    ResponseEntity<CreateGuildResponse> create(@RequestBody CreateGuildRequest createGuildRequest);

}

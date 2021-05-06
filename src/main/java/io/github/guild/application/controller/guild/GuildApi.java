package io.github.guild.application.controller.guild;

import io.github.guild.application.controller.guild.request.CreateGuildRequest;
import io.github.guild.application.controller.guild.response.CreateGuildResponse;
import io.github.guild.application.entity.GuildView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequestMapping("/guilds")
@Tag(name = "Guild Management API")
public interface GuildApi {

    @Operation(description = "Create guild")
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ApiResponse(responseCode = "204", description = "Successfully created a guild")
    @ApiResponse(responseCode = "400", description = "Bad request")
    ResponseEntity<CreateGuildResponse> create(@RequestBody CreateGuildRequest createGuildRequest);

    @Operation(description = "Delete guild")
    @DeleteMapping(value = "/{guildId}")
    @ApiResponse(responseCode = "204", description = "Successfully deleted a guild")
    @ApiResponse(responseCode = "404", description = "Not Found")
    ResponseEntity<Void> delete(@PathVariable @NotNull UUID guildId);

    @Operation(description = "Get guild by id")
    @GetMapping(value = "/{guildId}")
    @ApiResponse(responseCode = "200", description = "Guild found successfully")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "404", description = "Guild not found")
    ResponseEntity<GuildView> getByID(@PathVariable @NotNull UUID guildId);
}

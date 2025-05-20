package com.example.app.api;

import com.example.app.model.MessageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Message")
public interface MessageApi {

  @Operation(
      operationId = "messagesPost",
      summary = "Create message",
      responses = {@ApiResponse(responseCode = "201", description = "Created")})
  @RequestMapping(method = RequestMethod.POST, value = "/messages", consumes = {"application/json"})
  ResponseEntity<Void> messagesPost(@Parameter(name = "MessageDTO", required = true) @Valid @RequestBody MessageDTO messageDTO);

  @Operation(
      operationId = "messagesGet",
      summary = "Get message list",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MessageDTO.class))))
      })
  @RequestMapping(method = RequestMethod.GET, value = "/messages", produces = {"application/json"})
  ResponseEntity<List<MessageDTO>> messagesGet(@Parameter(name = "bookingId") @RequestParam(value = "bookingId", required = false) Long bookingId);
}

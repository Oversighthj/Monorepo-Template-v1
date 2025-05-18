package com.example.app.api;

import com.example.app.model.TaskDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Task")
public interface TaskApi {

    @Operation(operationId = "tasksPost", summary = "Create task", responses = {
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = {"application/json"})
    ResponseEntity<Void> tasksPost(@Parameter(name = "TaskDTO", required = true) @Valid @RequestBody TaskDTO taskDTO);

    @Operation(operationId = "tasksGet", summary = "Get task list", responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class)))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/tasks", produces = {"application/json"})
    ResponseEntity<List<TaskDTO>> tasksGet(
            @Parameter(name = "bookingId") @RequestParam(value = "bookingId", required = false) Long bookingId,
            @Parameter(name = "cleanerId") @RequestParam(value = "cleanerId", required = false) Long cleanerId);
}

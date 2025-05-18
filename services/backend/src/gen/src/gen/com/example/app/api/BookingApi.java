package com.example.app.api;

import com.example.app.model.BookingDTO;
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

@Tag(name = "Booking")
public interface BookingApi {

    @Operation(operationId = "bookingsPost", summary = "Create booking", responses = {
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @RequestMapping(method = RequestMethod.POST, value = "/bookings", consumes = {"application/json"})
    ResponseEntity<Void> bookingsPost(@Parameter(name = "BookingDTO", required = true) @Valid @RequestBody BookingDTO bookingDTO);

    @Operation(operationId = "bookingsGet", summary = "Get booking list", responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookingDTO.class)))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/bookings", produces = {"application/json"})
    ResponseEntity<List<BookingDTO>> bookingsGet(
            @Parameter(name = "propertyId") @RequestParam(value = "propertyId", required = false) Long propertyId,
            @Parameter(name = "userId") @RequestParam(value = "userId", required = false) Long userId);

    @Operation(operationId = "bookingsIdGet", summary = "Get booking", responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BookingDTO.class))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/bookings/{id}", produces = {"application/json"})
    ResponseEntity<BookingDTO> bookingsIdGet(@Parameter(name = "id", required = true) @PathVariable("id") Long id);

    @Operation(operationId = "bookingsIdPut", summary = "Update booking", responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BookingDTO.class))
            })
    })
    @RequestMapping(method = RequestMethod.PUT, value = "/bookings/{id}", consumes = {"application/json"}, produces = {"application/json"})
    ResponseEntity<BookingDTO> bookingsIdPut(
            @Parameter(name = "id", required = true) @PathVariable("id") Long id,
            @Parameter(name = "BookingDTO", required = true) @Valid @RequestBody BookingDTO bookingDTO);

    @Operation(operationId = "bookingsIdDelete", summary = "Delete booking", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted")
    })
    @RequestMapping(method = RequestMethod.DELETE, value = "/bookings/{id}")
    ResponseEntity<Void> bookingsIdDelete(@Parameter(name = "id", required = true) @PathVariable("id") Long id);
}

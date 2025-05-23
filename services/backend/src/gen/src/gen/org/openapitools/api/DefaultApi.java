/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import org.openapitools.model.FeatureDTO;
import org.openapitools.model.PropertyDTO;
import org.openapitools.model.UserDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-20T15:13:14.707770Z[Etc/UTC]")
@Validated
@Tag(name = "Default", description = "the Default API")
public interface DefaultApi {

    /**
     * GET /feature : Get feature list
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "featureGet",
        summary = "Get feature list",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FeatureDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/feature",
        produces = { "application/json" }
    )
    ResponseEntity<List<FeatureDTO>> featureGet(
        
    );


    /**
     * GET /properties : Get property list
     *
     * @param ownerId  (optional)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "propertiesGet",
        summary = "Get property list",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PropertyDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/properties",
        produces = { "application/json" }
    )
    ResponseEntity<List<PropertyDTO>> propertiesGet(
        @Parameter(name = "ownerId", description = "", in = ParameterIn.QUERY) @Valid @RequestParam(value = "ownerId", required = false) Long ownerId
    );


    /**
     * DELETE /properties/{id} : Delete property
     *
     * @param id  (required)
     * @return Deleted (status code 204)
     */
    @Operation(
        operationId = "propertiesIdDelete",
        summary = "Delete property",
        responses = {
            @ApiResponse(responseCode = "204", description = "Deleted")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/properties/{id}"
    )
    ResponseEntity<Void> propertiesIdDelete(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * GET /properties/{id} : Get property
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "propertiesIdGet",
        summary = "Get property",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PropertyDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/properties/{id}",
        produces = { "application/json" }
    )
    ResponseEntity<PropertyDTO> propertiesIdGet(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id
    );


    /**
     * PUT /properties/{id} : Update property
     *
     * @param id  (required)
     * @param propertyDTO  (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "propertiesIdPut",
        summary = "Update property",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = PropertyDTO.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/properties/{id}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    ResponseEntity<PropertyDTO> propertiesIdPut(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id,
        @Parameter(name = "PropertyDTO", description = "", required = true) @Valid @RequestBody PropertyDTO propertyDTO
    );


    /**
     * POST /properties : Create property
     *
     * @param propertyDTO  (required)
     * @return Created (status code 201)
     */
    @Operation(
        operationId = "propertiesPost",
        summary = "Create property",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/properties",
        consumes = { "application/json" }
    )
    ResponseEntity<Void> propertiesPost(
        @Parameter(name = "PropertyDTO", description = "", required = true) @Valid @RequestBody PropertyDTO propertyDTO
    );


    /**
     * GET /status : Status check
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "statusGet",
        summary = "Status check",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/status",
        produces = { "text/plain" }
    )
    ResponseEntity<String> statusGet(
        
    );


    /**
     * GET /users : Get user list
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "usersGet",
        summary = "Get user list",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users",
        produces = { "application/json" }
    )
    ResponseEntity<List<UserDTO>> usersGet(
        
    );


    /**
     * POST /users : Create user
     *
     * @param userDTO  (required)
     * @return Created (status code 201)
     */
    @Operation(
        operationId = "usersPost",
        summary = "Create user",
        responses = {
            @ApiResponse(responseCode = "201", description = "Created")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/users",
        consumes = { "application/json" }
    )
    ResponseEntity<Void> usersPost(
        @Parameter(name = "UserDTO", description = "", required = true) @Valid @RequestBody UserDTO userDTO
    );

}

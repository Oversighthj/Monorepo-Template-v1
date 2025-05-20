package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import org.openapitools.model.TaskStatus;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * TaskDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-20T15:13:14.707770Z[Etc/UTC]")
public class TaskDTO {

  private Long id;

  private Long bookingId;

  private Long cleanerId;

  private String type;

  private TaskStatus status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime due;

  /**
   * Default constructor
   * @deprecated Use {@link TaskDTO#TaskDTO(Long, Long, String, TaskStatus, OffsetDateTime)}
   */
  @Deprecated
  public TaskDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public TaskDTO(Long bookingId, Long cleanerId, String type, TaskStatus status, OffsetDateTime due) {
    this.bookingId = bookingId;
    this.cleanerId = cleanerId;
    this.type = type;
    this.status = status;
    this.due = due;
  }

  public TaskDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TaskDTO bookingId(Long bookingId) {
    this.bookingId = bookingId;
    return this;
  }

  /**
   * Get bookingId
   * @return bookingId
  */
  @NotNull 
  @Schema(name = "bookingId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("bookingId")
  public Long getBookingId() {
    return bookingId;
  }

  public void setBookingId(Long bookingId) {
    this.bookingId = bookingId;
  }

  public TaskDTO cleanerId(Long cleanerId) {
    this.cleanerId = cleanerId;
    return this;
  }

  /**
   * Get cleanerId
   * @return cleanerId
  */
  @NotNull 
  @Schema(name = "cleanerId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cleanerId")
  public Long getCleanerId() {
    return cleanerId;
  }

  public void setCleanerId(Long cleanerId) {
    this.cleanerId = cleanerId;
  }

  public TaskDTO type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @NotNull 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public TaskDTO status(TaskStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @NotNull @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public TaskStatus getStatus() {
    return status;
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public TaskDTO due(OffsetDateTime due) {
    this.due = due;
    return this;
  }

  /**
   * Get due
   * @return due
  */
  @NotNull @Valid 
  @Schema(name = "due", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("due")
  public OffsetDateTime getDue() {
    return due;
  }

  public void setDue(OffsetDateTime due) {
    this.due = due;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskDTO taskDTO = (TaskDTO) o;
    return Objects.equals(this.id, taskDTO.id) &&
        Objects.equals(this.bookingId, taskDTO.bookingId) &&
        Objects.equals(this.cleanerId, taskDTO.cleanerId) &&
        Objects.equals(this.type, taskDTO.type) &&
        Objects.equals(this.status, taskDTO.status) &&
        Objects.equals(this.due, taskDTO.due);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, bookingId, cleanerId, type, status, due);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    bookingId: ").append(toIndentedString(bookingId)).append("\n");
    sb.append("    cleanerId: ").append(toIndentedString(cleanerId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    due: ").append(toIndentedString(due)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


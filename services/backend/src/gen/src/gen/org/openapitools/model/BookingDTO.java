package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import org.openapitools.model.BookingStatus;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BookingDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-20T15:13:14.707770Z[Etc/UTC]")
public class BookingDTO {

  private Long id;

  private Long propertyId;

  private Long userId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime startAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime endAt;

  private BookingStatus status;

  /**
   * Default constructor
   * @deprecated Use {@link BookingDTO#BookingDTO(Long, Long, OffsetDateTime, OffsetDateTime, BookingStatus)}
   */
  @Deprecated
  public BookingDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BookingDTO(Long propertyId, Long userId, OffsetDateTime startAt, OffsetDateTime endAt, BookingStatus status) {
    this.propertyId = propertyId;
    this.userId = userId;
    this.startAt = startAt;
    this.endAt = endAt;
    this.status = status;
  }

  public BookingDTO id(Long id) {
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

  public BookingDTO propertyId(Long propertyId) {
    this.propertyId = propertyId;
    return this;
  }

  /**
   * Get propertyId
   * @return propertyId
  */
  @NotNull 
  @Schema(name = "propertyId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("propertyId")
  public Long getPropertyId() {
    return propertyId;
  }

  public void setPropertyId(Long propertyId) {
    this.propertyId = propertyId;
  }

  public BookingDTO userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  */
  @NotNull 
  @Schema(name = "userId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userId")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public BookingDTO startAt(OffsetDateTime startAt) {
    this.startAt = startAt;
    return this;
  }

  /**
   * Get startAt
   * @return startAt
  */
  @NotNull @Valid 
  @Schema(name = "startAt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("startAt")
  public OffsetDateTime getStartAt() {
    return startAt;
  }

  public void setStartAt(OffsetDateTime startAt) {
    this.startAt = startAt;
  }

  public BookingDTO endAt(OffsetDateTime endAt) {
    this.endAt = endAt;
    return this;
  }

  /**
   * Get endAt
   * @return endAt
  */
  @NotNull @Valid 
  @Schema(name = "endAt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("endAt")
  public OffsetDateTime getEndAt() {
    return endAt;
  }

  public void setEndAt(OffsetDateTime endAt) {
    this.endAt = endAt;
  }

  public BookingDTO status(BookingStatus status) {
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
  public BookingStatus getStatus() {
    return status;
  }

  public void setStatus(BookingStatus status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookingDTO bookingDTO = (BookingDTO) o;
    return Objects.equals(this.id, bookingDTO.id) &&
        Objects.equals(this.propertyId, bookingDTO.propertyId) &&
        Objects.equals(this.userId, bookingDTO.userId) &&
        Objects.equals(this.startAt, bookingDTO.startAt) &&
        Objects.equals(this.endAt, bookingDTO.endAt) &&
        Objects.equals(this.status, bookingDTO.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, propertyId, userId, startAt, endAt, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BookingDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    propertyId: ").append(toIndentedString(propertyId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    startAt: ").append(toIndentedString(startAt)).append("\n");
    sb.append("    endAt: ").append(toIndentedString(endAt)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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


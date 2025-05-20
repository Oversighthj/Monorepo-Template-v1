package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MessageDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-20T15:13:14.707770Z[Etc/UTC]")
public class MessageDTO {

  private Long id;

  private Long bookingId;

  private Long senderId;

  private String content;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime sentAt;

  /**
   * Default constructor
   * @deprecated Use {@link MessageDTO#MessageDTO(Long, Long, String, OffsetDateTime)}
   */
  @Deprecated
  public MessageDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MessageDTO(Long bookingId, Long senderId, String content, OffsetDateTime sentAt) {
    this.bookingId = bookingId;
    this.senderId = senderId;
    this.content = content;
    this.sentAt = sentAt;
  }

  public MessageDTO id(Long id) {
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

  public MessageDTO bookingId(Long bookingId) {
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

  public MessageDTO senderId(Long senderId) {
    this.senderId = senderId;
    return this;
  }

  /**
   * Get senderId
   * @return senderId
  */
  @NotNull 
  @Schema(name = "senderId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("senderId")
  public Long getSenderId() {
    return senderId;
  }

  public void setSenderId(Long senderId) {
    this.senderId = senderId;
  }

  public MessageDTO content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  */
  @NotNull 
  @Schema(name = "content", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("content")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public MessageDTO sentAt(OffsetDateTime sentAt) {
    this.sentAt = sentAt;
    return this;
  }

  /**
   * Get sentAt
   * @return sentAt
  */
  @NotNull @Valid 
  @Schema(name = "sentAt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sentAt")
  public OffsetDateTime getSentAt() {
    return sentAt;
  }

  public void setSentAt(OffsetDateTime sentAt) {
    this.sentAt = sentAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessageDTO messageDTO = (MessageDTO) o;
    return Objects.equals(this.id, messageDTO.id) &&
        Objects.equals(this.bookingId, messageDTO.bookingId) &&
        Objects.equals(this.senderId, messageDTO.senderId) &&
        Objects.equals(this.content, messageDTO.content) &&
        Objects.equals(this.sentAt, messageDTO.sentAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, bookingId, senderId, content, sentAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessageDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    bookingId: ").append(toIndentedString(bookingId)).append("\n");
    sb.append("    senderId: ").append(toIndentedString(senderId)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    sentAt: ").append(toIndentedString(sentAt)).append("\n");
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


package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import io.swagger.v3.oas.annotations.media.Schema;

@Generated(value = "manual")
public class PropertyDTO {

  private Long id;
  private String name;
  private String address;
  private Long ownerId;

  public PropertyDTO id(Long id) {
    this.id = id;
    return this;
  }

  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PropertyDTO name(String name) {
    this.name = name;
    return this;
  }

  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PropertyDTO address(String address) {
    this.address = address;
    return this;
  }

  @Schema(name = "address", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public PropertyDTO ownerId(Long ownerId) {
    this.ownerId = ownerId;
    return this;
  }

  @Schema(name = "ownerId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ownerId")
  public Long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(Long ownerId) {
    this.ownerId = ownerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PropertyDTO that = (PropertyDTO) o;
    return Objects.equals(id, that.id) &&
           Objects.equals(name, that.name) &&
           Objects.equals(address, that.address) &&
           Objects.equals(ownerId, that.ownerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, address, ownerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PropertyDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    ownerId: ").append(toIndentedString(ownerId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

package org.openapitools.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Generated(value = "manual")
public class UserDTO {

  private Long id;
  private UserRole role;
  private String email;
  private String passwordHash;

  public UserDTO id(Long id) {
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

  public UserDTO role(UserRole role) {
    this.role = role;
    return this;
  }

  @Schema(name = "role", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("role")
  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public UserDTO email(String email) {
    this.email = email;
    return this;
  }

  @Schema(name = "email", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserDTO passwordHash(String passwordHash) {
    this.passwordHash = passwordHash;
    return this;
  }

  @Schema(name = "passwordHash", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("passwordHash")
  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDTO userDTO = (UserDTO) o;
    return Objects.equals(this.id, userDTO.id) &&
        Objects.equals(this.role, userDTO.role) &&
        Objects.equals(this.email, userDTO.email) &&
        Objects.equals(this.passwordHash, userDTO.passwordHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, role, email, passwordHash);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    passwordHash: ").append(toIndentedString(passwordHash)).append("\n");
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

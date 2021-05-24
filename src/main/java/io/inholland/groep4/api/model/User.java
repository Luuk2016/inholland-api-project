package io.inholland.groep4.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-24T18:28:14.004Z[GMT]")


public class User   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("birthdate")
  private String birthdate = null;

  /**
   * Gets or Sets accessLevel
   */
  public enum AccessLevelEnum {
    CUSTOMER("Customer"),
    
    EMPLOYEE("Employee");

    private String value;

    AccessLevelEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AccessLevelEnum fromValue(String text) {
      for (AccessLevelEnum b : AccessLevelEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("accessLevel")
  @Valid
  private List<AccessLevelEnum> accessLevel = new ArrayList<AccessLevelEnum>();

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    ACTIVE("Active"),
    
    INACTIVE("Inactive");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  @Valid
  private List<StatusEnum> status = null;

  public User id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * minimum: 1
   * @return id
   **/
  @Schema(example = "9999", required = true, description = "")
      @NotNull

  @Min(1L)  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   **/
  @Schema(example = "John", required = true, description = "")
      @NotNull

    public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
   **/
  @Schema(example = "Doe", required = true, description = "")
      @NotNull

    public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  @Schema(example = "johndoe@groep4API.com", required = true, description = "")
      @NotNull

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   **/
  @Schema(example = "johndoe", required = true, description = "")
      @NotNull

    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
   **/
  @Schema(example = "testPassword", required = true, description = "")
      @NotNull

    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User birthdate(String birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  /**
   * Get birthdate
   * @return birthdate
   **/
  @Schema(example = "1969-04-20", description = "")
  
    public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public User accessLevel(List<AccessLevelEnum> accessLevel) {
    this.accessLevel = accessLevel;
    return this;
  }

  public User addAccessLevelItem(AccessLevelEnum accessLevelItem) {
    this.accessLevel.add(accessLevelItem);
    return this;
  }

  /**
   * Get accessLevel
   * @return accessLevel
   **/
  @Schema(example = "Employee", required = true, description = "")
      @NotNull

    public List<AccessLevelEnum> getAccessLevel() {
    return accessLevel;
  }

  public void setAccessLevel(List<AccessLevelEnum> accessLevel) {
    this.accessLevel = accessLevel;
  }

  public User status(List<StatusEnum> status) {
    this.status = status;
    return this;
  }

  public User addStatusItem(StatusEnum statusItem) {
    if (this.status == null) {
      this.status = new ArrayList<StatusEnum>();
    }
    this.status.add(statusItem);
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  @Schema(example = "Active", description = "")
  
    public List<StatusEnum> getStatus() {
    return status;
  }

  public void setStatus(List<StatusEnum> status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.firstName, user.firstName) &&
        Objects.equals(this.lastName, user.lastName) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.username, user.username) &&
        Objects.equals(this.password, user.password) &&
        Objects.equals(this.birthdate, user.birthdate) &&
        Objects.equals(this.accessLevel, user.accessLevel) &&
        Objects.equals(this.status, user.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, email, username, password, birthdate, accessLevel, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
    sb.append("    accessLevel: ").append(toIndentedString(accessLevel)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

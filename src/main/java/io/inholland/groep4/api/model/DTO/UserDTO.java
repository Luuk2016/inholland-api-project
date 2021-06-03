package io.inholland.groep4.api.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class UserDTO {

    @Schema(example = "johndoe", required = true, description = "")
    @NotNull
    private String username;

    @Schema(example = "helloworld", required = true, description = "")
    @NotNull
    private String password;

    @Schema(example = "john", required = true, description = "")
    @NotNull
    private String firstName;

    @Schema(example = "doe", required = true, description = "")
    @NotNull
    private String lastName;

    @Schema(example = "johndoe@email.com", required = true, description = "")
    @NotNull
    private String email;

    @Schema(example = "01/01/1990", required = true, description = "")
    @NotNull
    private String birthdate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}

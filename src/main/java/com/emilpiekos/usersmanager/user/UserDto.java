package com.emilpiekos.usersmanager.user;

import jakarta.validation.constraints.*;

public class UserDto {

    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 30, message = "Username must have 2 to 30 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 20, message = "First name must have 3 to 20 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 20, message = "Last name must have 3 to 20 characters")
    private String lastName;

    @NotBlank(message = "E-mail address is required")
    @Email(message = "E-mail address must match pattern \"example@mail.com\"")
    private String email;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "[+][0-9]{1,3}-[0-9]{3}-[0-9]{3}-[0-9]{3,4}", message = "Phone number must match pattern +3-333-333-4444")
    private String phoneNumber;

    public @NotBlank(message = "Username is required") @Size(min = 2, max = 30, message = "Username must have 2 to 30 characters") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is required") @Size(min = 2, max = 30, message = "Username must have 2 to 30 characters") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must have at least 8 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must have at least 8 characters") String password) {
        this.password = password;
    }

    public @NotBlank(message = "First name is required") @Size(min = 3, max = 20, message = "First name must have 3 to 20 characters") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First name is required") @Size(min = 3, max = 20, message = "First name must have 3 to 20 characters") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Last name is required") @Size(min = 3, max = 20, message = "Last name must have 3 to 20 characters") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last name is required") @Size(min = 3, max = 20, message = "Last name must have 3 to 20 characters") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "E-mail address is required") @Email(message = "E-mail address must match pattern \"example@mail.com\"") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "E-mail address is required") @Email(message = "E-mail address must match pattern \"example@mail.com\"") String email) {
        this.email = email;
    }

    public @NotNull(message = "Phone number is required") @Pattern(regexp = "[+][0-9]{1,3}-[0-9]{3}-[0-9]{3}-[0-9]{3,4}", message = "Phone number must match pattern +3-333-333-4444") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull(message = "Phone number is required") @Pattern(regexp = "[+][0-9]{1,3}-[0-9]{3}-[0-9]{3}-[0-9]{3,4}", message = "Phone number must match pattern +3-333-333-4444") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
package com.emilpiekos.usersmanager.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @NotBlank(message = "Address is required")
    @Size(min = 10, max = 50, message = "Address must have at least 10 characters")
    private String address;

    @NotBlank(message = "E-mail address is required")
    @Email(message = "E-mail address must match pattern \"example@mail.com\"")
    private String email;

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "[+][0-9]{1,3}-[0-9]{3}-[0-9]{3}-[0-9]{3,4}", message = "Phone number must match pattern +3-333-333-4444")
    private String phoneNumber;
}
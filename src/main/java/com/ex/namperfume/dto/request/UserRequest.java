package com.ex.namperfume.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRequest {
    @NotBlank(message = "First name is required")
    String firstName;

    @NotBlank(message = "Last name is required")
    String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    String email;

    @Pattern(regexp = "^[0-9]{10,11}$", message = "Invalid phone number")
    String phone;

    @Size(min = 8, message = "Password must be greater than 8 characters")
    @NotBlank(message = "Password is required")
    String password;

    @Past(message = "Date of birth must be in the past")
    LocalDate dateOfBirth;

    @NotBlank(message = "Shipping address is required")
    String shippingAddress;
}

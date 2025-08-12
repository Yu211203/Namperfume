package com.ex.namperfume.dto.response;

import com.ex.namperfume.entity.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    UUID user_id;
    String firstName;
    String lastName;
    String email;
    String phone;
    String password;
    LocalDate dateOfBirth;
    String shippingAddress;
    List<Order> orders;
    Set<RoleResponse> roles;
}

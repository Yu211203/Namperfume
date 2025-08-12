package com.ex.namperfume.dto.response;

import com.ex.namperfume.entity.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    String role_name;
    String role_description;
    List<PermissionResponse> permissions;
}

package com.ex.namperfume.dto.response;

import com.ex.namperfume.entity.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
    UUID role_id;
    String roleName;
    String roleDescription;
    List<PermissionResponse> permissions;
}

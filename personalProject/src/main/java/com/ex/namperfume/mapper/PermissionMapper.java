package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.PermissionRequest;
import com.ex.namperfume.dto.response.PermissionResponse;
import com.ex.namperfume.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}

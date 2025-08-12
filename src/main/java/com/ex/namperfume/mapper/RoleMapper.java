package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.RoleRequest;
import com.ex.namperfume.dto.response.RoleResponse;
import com.ex.namperfume.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole (RoleRequest request);

    RoleResponse toRoleResponse (Role role);

}

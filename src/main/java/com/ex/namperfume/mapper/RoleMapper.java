package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.RoleRequest;
import com.ex.namperfume.dto.response.RoleResponse;
import com.ex.namperfume.entity.Role;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole (RoleRequest request);

    RoleResponse toRoleResponse (Role role);

    @Named("nameToRole")
    default Role nameToRole(String name){
        if (name == null) return null;
        Role r = new Role();

        r.setRole_name(name);
        return r;
    }

    @Named("namesToRoles")
    default Set<Role> namesToRoles(Set<String> names)
    {
        if(names == null) return java.util.Collections.emptySet();
        return names.stream().map(this::nameToRole).collect(Collectors.toSet());

    }
}

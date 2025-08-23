package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.RoleRequest;
import com.ex.namperfume.dto.response.RoleResponse;
import com.ex.namperfume.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

    @Named("nameToRole")
    default Role nameToRole(String roleName) {
        if (roleName == null) return null;
        Role role = new Role();
        role.setRoleName(roleName); // chỉ set tên
        return role;
    }

    @Named("namesToRoles")
    default Set<Role> namesToRoles(Set<String> roleNames) {
        if (roleNames == null) return java.util.Collections.emptySet();
        return roleNames.stream().map(this::nameToRole).collect(Collectors.toSet());
    }
}

//@Mapper(componentModel = "spring")
//public interface RoleMapper {
//
//    @Mapping(target = "permissions", ignore = true)
//    Role toRole (RoleRequest request);
//
//    RoleResponse toRoleResponse (Role role);
//
//    @Named("nameToRole")
//    default Role nameToRole(String role_name){
//        if (role_name == null) return null;
//        Role r = new Role();
//
//        r.setRoleName(role_name);
//        return r;
//    }
//
//    @Named("namesToRoles")
//    default Set<Role> namesToRoles(Set<String> role_names)
//    {
//        if(role_names == null) return java.util.Collections.emptySet();
//        return role_names.stream().map(this::nameToRole).collect(Collectors.toSet());
//
//    }
//}

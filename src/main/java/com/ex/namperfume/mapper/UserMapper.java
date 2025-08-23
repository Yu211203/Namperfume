package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.UserRequest;
import com.ex.namperfume.dto.response.UserResponse;
import com.ex.namperfume.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    @Mapping(target = "roles", source = "roles", qualifiedByName = "namesToRoles")
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true) // tránh ghi đè roles khi update
    void updateUser(@MappingTarget User user, UserRequest request);
}

//@Mapper(componentModel = "spring",uses = RoleMapper.class)
//public interface UserMapper {
//    @Mapping(target = "roles", source = "roles", qualifiedByName = "namesToRoles")
//    User toUser(UserRequest request);
//
//    UserResponse toUserResponse(User user);
//
//    @Mapping(target = "roles", ignore = true)
//    void updateUser(@MappingTarget User user, UserRequest request);
//}

package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.UserRequest;
import com.ex.namperfume.dto.response.UserResponse;
import com.ex.namperfume.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user);
}

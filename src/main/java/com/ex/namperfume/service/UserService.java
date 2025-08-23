package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.UserRequest;
import com.ex.namperfume.dto.response.UserResponse;
import com.ex.namperfume.entity.Role;
import com.ex.namperfume.entity.User;
import com.ex.namperfume.exception.AppException;
import com.ex.namperfume.exception.EnumCode;
import com.ex.namperfume.mapper.RoleMapper;
import com.ex.namperfume.mapper.UserMapper;
import com.ex.namperfume.repository.RoleRepository;
import com.ex.namperfume.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    private final RoleMapper roleMapper;
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createUser(UserRequest request){
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        try{
            if(request.getRoles() != null && !request.getRoles().isEmpty()){
                Set<Role> roles = request.getRoles().stream().map(roleName -> roleRepository.findByRoleNameIgnoreCase(roleName)
                        .orElseThrow(()-> new RuntimeException("Role not found: "+roleName)))
                        .collect(Collectors.toSet());

                user.setRoles(roles);
            }
            userRepository.save(user);

        } catch (AppException e) {
            throw new AppException(EnumCode.UNCATEGORIZED_EXCEPTION);
        }
        return userMapper.toUserResponse(user);
    }

    public UserResponse getUser(UUID user_id){
        User user = userRepository.findById(user_id).orElseThrow(()->new AppException(EnumCode.USER_NOT_EXIST));
        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public void deleteUser(UUID user_id){
        if(!userRepository.existsById(user_id))
            throw new AppException(EnumCode.USER_NOT_EXIST);
        userRepository.deleteById(user_id);
    }

    public UserResponse addRoleToUser(UUID user_id, UUID role_id){
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new AppException(EnumCode.USER_NOT_EXIST));
        Role role = roleRepository.findById(role_id).orElseThrow(()-> new AppException(EnumCode.ROLE_NOT_EXIST));
        user.getRoles().add(role);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse removeRoleFromUser(UUID user_id, String roleName){
        User user = userRepository.findById(user_id).orElseThrow(()-> new AppException(EnumCode.USER_NOT_EXIST));

        user.getRoles().removeIf(r -> r.getRoleName().equals(roleName));
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(UUID user_id, UserRequest request) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new AppException(EnumCode.USER_NOT_EXIST));

        userMapper.updateUser(user, request);

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getRoles() != null && !request.getRoles().isEmpty()) {
            Set<Role> roles = request.getRoles().stream()
                    .map(roleName -> roleRepository.findByRoleNameIgnoreCase(roleName)
                            .orElseThrow(() -> new AppException(EnumCode.ROLE_NOT_EXIST)))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

//    public UserResponse updateUser(UUID user_id, UserRequest request){
//        User user = userRepository.findById(user_id).orElseThrow(()-> new AppException(EnumCode.USER_NOT_EXIST));
//
//        userMapper.updateUser(user, request);
//
//        if(request.getPassword() != null && !request.getPassword().isBlank()){
//            user.setPassword(passwordEncoder.encode(request.getPassword()));
//        }
//
//        if(request.getRoles() != null){
//            Set<Role> roles = new HashSet<>(roleRepository.findAllById(request.getRoles()));
//            if(roles.size() != request.getRoles().size()){
//                throw new AppException(EnumCode.ROLE_NOT_EXIST);
//            }
//            user.setRoles(roles);
//        }
//        user = userRepository.save(user);
//        return userMapper.toUserResponse(user);
//    }
}

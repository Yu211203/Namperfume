package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.UserRequest;
import com.ex.namperfume.dto.response.UserResponse;
import com.ex.namperfume.entity.User;
import com.ex.namperfume.mapper.UserMapper;
import com.ex.namperfume.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest request){
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        try{
            userRepository.save(user);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return userMapper.toUserResponse(user);
    }

    public UserResponse getUser(UUID user_id){
        User user = userRepository.findById(user_id).orElseThrow(()->new RuntimeException("Not found user"));
        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public void deleteUser(UUID user_id){
        userRepository.deleteById(user_id);
    }
}

package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.UserRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.UserResponse;
import com.ex.namperfume.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody UserRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{user_id}")
    public ApiResponse<UserResponse> getUser(@PathVariable("user_id")UUID user_id){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(user_id))
                .build();
    }

    @DeleteMapping("/{user_id}")
    public String deleteUser(@PathVariable("user_id")UUID user_id){
        userService.deleteUser(user_id);
        return "User deleted successfully";
    }
}

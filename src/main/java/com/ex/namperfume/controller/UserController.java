package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.UserRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.UserResponse;
import com.ex.namperfume.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers(){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN') or #user_id.toString() == authentication.principal.claims['sub']")
    @GetMapping("/{user_id}")
    public ApiResponse<UserResponse> getUser(@PathVariable("user_id")UUID user_id){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(user_id))
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{user_id}")
    public ApiResponse<Void> deleteUser(@PathVariable("user_id")UUID user_id){
        userService.deleteUser(user_id);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Deleted successfully")
                .build();
    }

    @PreAuthorize("#user_id.toString() == authentication.principal.claims['sub']")
    @PutMapping("/{user_id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable UUID user_id, @Valid @RequestBody UserRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(user_id, request))
                .build();
    }
}

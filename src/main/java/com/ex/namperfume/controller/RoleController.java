package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.RoleRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.RoleResponse;
import com.ex.namperfume.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request)
    {
        ApiResponse<RoleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roleService.createRole(request));
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<RoleResponse>> getRoles(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getRoles())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{role_name}")
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable("role_name") String role_name){
        roleService.deleteRole(role_name);
        return ResponseEntity.noContent().build();
    }
}

package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.RoleRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.RoleResponse;
import com.ex.namperfume.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

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

    @DeleteMapping("/{role_name}")
    public String deleteRole(@PathVariable("role_name") String role_name){
        roleService.deleteRole(role_name);
        return "Role deleted successfully";
    }
}

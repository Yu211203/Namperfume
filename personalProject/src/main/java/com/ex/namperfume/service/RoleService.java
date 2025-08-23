package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.RoleRequest;
import com.ex.namperfume.dto.response.RoleResponse;
import com.ex.namperfume.entity.Permission;
import com.ex.namperfume.entity.Role;
import com.ex.namperfume.mapper.RoleMapper;
import com.ex.namperfume.repository.PermissionRepository;
import com.ex.namperfume.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse createRole(RoleRequest request){
        Role role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermission());

        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getRoles(){
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).collect(Collectors.toList());

    }

    public void deleteRole(String role){
        roleRepository.deleteById(role);
    }
}

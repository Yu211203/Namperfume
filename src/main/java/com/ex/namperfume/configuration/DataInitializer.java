package com.ex.namperfume.configuration;

import com.ex.namperfume.entity.Permission;
import com.ex.namperfume.entity.Role;
import com.ex.namperfume.entity.User;
import com.ex.namperfume.mapper.RoleMapper;
import com.ex.namperfume.repository.PermissionRepository;
import com.ex.namperfume.repository.RoleRepository;
import com.ex.namperfume.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleMapper roleMapper;

    @Override
    public void run(String...args){

        Permission viewUser = permissionRepository.findByPermissionNameIgnoreCase("VIEW_USERS")
                .orElseGet(() -> permissionRepository.save(Permission.builder().permissionName("VIEW_USERS").build()));

        Permission editUser = permissionRepository.findByPermissionNameIgnoreCase("EDIT_USERS")
                .orElseGet(() -> permissionRepository.save(Permission.builder().permissionName("EDIT_USERS").build()));

        Permission viewProduct = permissionRepository.findByPermissionNameIgnoreCase("VIEW_PRODUCT")
                .orElseGet(() -> permissionRepository.save(Permission.builder().permissionName("VIEW_PRODUCT").build()));


        Role adminRole = roleRepository.findByRoleNameIgnoreCase("ADMIN").orElseGet(
                ()->{
                    Role newAdminRole = new Role("ADMIN");
                    newAdminRole.addPermission(viewUser);
                    newAdminRole.addPermission(editUser);
                    newAdminRole.addPermission(viewProduct);
                    return roleRepository.save(newAdminRole);
                }
        );

        Role userRole = roleRepository.findByRoleNameIgnoreCase("USER").orElseGet(
                ()-> {
                    Role newUserRole = new Role("USER");
                    newUserRole.addPermission(viewProduct);
                    return roleRepository.save(newUserRole);
                }
        );

        if(userRepository.findByEmail("admin@gmail.com").isEmpty()){
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);
            System.out.println("Seeded admin: admin@gmail.com / admin");
        }

        if(userRepository.findByEmail("user@gmail.com").isEmpty()){
            User user = new User();
            user.setEmail("user@gmail.com");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
            System.out.println("Seeded user: user@gmail.com / user");
        }
    }
}

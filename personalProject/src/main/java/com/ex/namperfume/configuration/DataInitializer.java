package com.ex.namperfume.configuration;

import com.ex.namperfume.entity.Role;
import com.ex.namperfume.entity.User;
import com.ex.namperfume.mapper.RoleMapper;
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
    private final PasswordEncoder passwordEncoder;
    private final RoleMapper roleMapper;

    @Override
    public void run(String...args){
        Role adminRole = roleRepository.findByRoleNameIgnoreCase("ADMIN").orElseGet(
                ()-> roleRepository.save(new Role("ADMIN"))
        );

        Role userRole = roleRepository.findByRoleNameIgnoreCase("USER").orElseGet(
                ()-> roleRepository.save(new Role("USER"))
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

package com.ex.namperfume.configuration;

import com.ex.namperfume.constant.PredefinedRole;
import com.ex.namperfume.entity.Role;
import com.ex.namperfume.entity.User;
import com.ex.namperfume.repository.RoleRepository;
import com.ex.namperfume.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME="admin";

    @NonFinal
    static final String ADMIN_PASSWORD="admin";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver"
    )
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository){
        log.info("Initializing application...");

        return args -> {
            if(userRepository.findByEmail(ADMIN_USER_NAME).isEmpty())
            {
                roleRepository.save(Role.builder()
                                .roleName(PredefinedRole.USER_ROLE)
                                .roleDescription("User role")
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                                .roleName(PredefinedRole.ADMIN_ROLE)
                                .roleDescription("Admin role")
                        .build());

                var roles = new HashSet<Role>();
                roles.add(adminRole);

                User user = User.builder()
                        .email(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(roles)
                        .build();

                userRepository.save(user);
                log.warn("Admin user has been create with default password: admin, please change it");
            }
            log.info("Application initialization completed...");
        };
    }
}

package dev.jacksd.iprwc.api.Seeder;

import dev.jacksd.iprwc.api.Service.UserService;
import dev.jacksd.iprwc.api.enums.Role;
import dev.jacksd.iprwc.api.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Seeder {

    @Value("${super-admin.email}")
    String adminEmail;

    @Value("${super-admin.password}")
    String adminPassword;

    User admin = new User();

    @Bean
    CommandLineRunner commandLineRunner(UserService userService, PasswordEncoder passwordEncoder) {

        return args -> {

            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(Role.ADMIN);
            admin.setFirstname("Admin");

            userService.save(admin);
        };
    }
}

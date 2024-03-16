package dev.jacksd.iprwc.api.Seeder;

import dev.jacksd.iprwc.api.Service.OrderService;
import dev.jacksd.iprwc.api.Service.ProductService;
import dev.jacksd.iprwc.api.enums.Role;
import dev.jacksd.iprwc.api.model.*;
import dev.jacksd.iprwc.api.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class Seeder {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository, OrderService orderService) {
        return args -> {

            String adminPassword;

            BCryptPasswordEncoder bCryptPasswordEncoder;

            User admin = new User();

            admin.setRole(Role.ADMIN);
            admin.setEmail("jackdijkema@hotmail.com");
            admin.setPassword("admin");


            userRepository.save(admin);


        };
    }
}

package dev.jacksd.iprwc.api.Seeder;

import dev.jacksd.iprwc.api.model.Product;
import dev.jacksd.iprwc.api.model.User;
import dev.jacksd.iprwc.api.repository.ProductRepository;
import dev.jacksd.iprwc.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserSeeder {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ProductRepository productRepository) {
        return args -> {
            User jack = new User("Jack", "password");
            User pieter = new User("Pieter", "password");

            Product product1 = new Product("Product1", 10.50, "a nice product 1", "https://example.com/example.png");
            Product product2 = new Product("Product2", 12.50, "a nice product 2", "https://example.com/example.png");

            userRepository.saveAll(List.of(jack, pieter));
            productRepository.saveAll(List.of(product1, product2));

        };
    }
}

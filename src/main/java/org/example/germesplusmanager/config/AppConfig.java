package org.example.germesplusmanager.config;

import lombok.AllArgsConstructor;
import org.example.germesplusmanager.enums.Role;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.repository.PointManagerRepository;
import org.example.germesplusmanager.service.PointOfSaleService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class AppConfig {

    private final PointManagerRepository pointManagerRepository;
    private final PasswordEncoder passwordEncoder;
    private final PointOfSaleService pointOfSaleService;

    @Bean
    public ApplicationRunner initializeUsers() {
        PointOfSale point = pointOfSaleService.getById(1L);
        return args -> {
            initializeUserIfNotExists(
                "manager@mail.ru",
                    "Manager",
                    "Name",
                    "1234567890",
                    passwordEncoder.encode("manager"),
                    Role.USER,
                    point
            );

            initializeUserIfNotExists(
                    "admin@mail.ru",
                    "Admin",
                    "Name",
                    "1234567890",
                    passwordEncoder.encode("admin"),
                    Role.ADMIN,
                    point
            );
        };
    }

    private void initializeUserIfNotExists(String email,
                                           String surname,
                                           String name,
                                           String phoneNumber,
                                           String password,
                                           Role role,
                                           PointOfSale pointOfSale) {
        if (!pointManagerRepository.existsByEmail(email)) {
            PointManager user = PointManager.builder()
                    .email(email)
                    .surname(surname)
                    .name(name)
                    .phoneNumber(phoneNumber)
                    .password(password)
                    .role(role)
                    .pointOfSale(pointOfSale)
                    .build();
            pointManagerRepository.save(user);
        }
    }
}

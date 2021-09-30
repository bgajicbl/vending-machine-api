package com.bojan.vending;

import com.bojan.vending.dto.ProductDto;
import com.bojan.vending.dto.UserDto;
import com.bojan.vending.service.ProductService;
import com.bojan.vending.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final List<String> usernames = List.of(
            "seller",
            "buyer",
            "admin"
    );
    private final List<String> fullNames = List.of(
            "Ada Lovelace",
            "Alan Turing",
            "Dennis Ritchie"
    );
    private final List<String> roles = List.of(
            "ROLE_SELLER",
            "ROLE_BUYER",
            "ROLE_ADMIN"
    );
    private final List<String> products = List.of(
            "soda",
            "candy bar",
            "water"
    );
    public static final String PASSWORD = "test";

    private final UserService userService;
    private final ProductService productService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //add roles
        for (String roleName : roles) {
            userService.createRole(roleName);
        }

        //add users and products
        for (int i = 0; i < usernames.size(); ++i) {
            UserDto user = UserDto.builder()
                    .username(usernames.get(i))
                    .password(PASSWORD)
                    .deposit(new Random().nextInt(500))
                    .roles(Set.of(userService.findRoleByName(roles.get(i))))
                    .build();
            userService.register(user);

            ProductDto product = ProductDto.builder()
                    .productName(products.get(i))
                    .cost(new Random().nextInt(50))
                    .amountAvailable(new Random().nextInt(20))
                    .build();
            productService.create(product, usernames.get(i));

        }
    }

}

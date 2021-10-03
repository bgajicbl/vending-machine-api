package com.bojan.vending.data;

import com.bojan.vending.dto.ProductDto;
import com.bojan.vending.dto.UserDto;
import com.bojan.vending.model.Role;

import java.util.Set;

public class UserDTOFactory {

    private UserDTOFactory() {
        // Private constructor to hide the implicit public one.
    }

    public static UserDto createWithDefaultValues(long id) {
        Role role = RoleFactory.createWithDefaultValues(1);

        return UserDto.builder()
                .id(id)
                .username("test_user")
                .password("test")
                .enabled(true)
                .roles(Set.of(role))
                .build();
    }

    public static UserDto createWithoutId() {
        Role role = RoleFactory.createWithDefaultValues(1);

        return UserDto.builder()
                .username("test_user")
                .password("test")
                .enabled(true)
                .roles(Set.of(role))
                .build();
    }
}

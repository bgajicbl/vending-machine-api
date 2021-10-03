package com.bojan.vending.data;

import com.bojan.vending.model.Product;
import com.bojan.vending.model.Role;

public class RoleFactory {

    private RoleFactory() {

    }

    public static Role createWithDefaultValues(long id) {
        return Role.builder()
                .id(1)
                .name("ROLE_ADMIN")
                .build();
    }

    public static Role createWithoutId() {
        return Role.builder()
                .name("ROLE_ADMIN")
                .build();
    }
}

package com.bojan.vending.dto.mapper;

import com.bojan.vending.dto.UserDto;
import com.bojan.vending.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 *
 */
@Component
public class UserMapper {

    public static UserDto toUserDto(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password("*****")
                .enabled(user.getEnabled())
                .deposit(user.getDeposit())
                .roles(user.getRoles())
                .build();
    }

     public static User toUser (UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .enabled(userDto.getEnabled())
                .deposit(userDto.getDeposit())
                .build();
    }

    public static User updateUser(User user, UserDto userDto){
        user.setUsername(userDto.getUsername());
        user.setDeposit(user.getDeposit());
        user.setEnabled(userDto.getEnabled());

        return user;
    }
}

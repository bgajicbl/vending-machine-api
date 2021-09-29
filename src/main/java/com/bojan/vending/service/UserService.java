package com.bojan.vending.service;

import com.bojan.vending.dto.UserDto;
import com.bojan.vending.model.Role;

public interface UserService {

    UserDto register(UserDto userDto);

    UserDto findUserByUsername(String username);
    UserDto findUserById(long id);
    UserDto updateUser(long id, UserDto userDto);
    void deleteUser(long id);
    void createRole(String roleName);
    Role findRoleByName(String roleName);


}

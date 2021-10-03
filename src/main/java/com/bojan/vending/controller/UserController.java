package com.bojan.vending.controller;

import com.bojan.vending.dto.AuthRequest;
import com.bojan.vending.dto.UserDto;
import com.bojan.vending.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "user", description = "the User API")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "User login", description = "User login", tags = { "user" })
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody @Valid AuthRequest request) {
        return userService.login(request);

    }

    @Operation(summary = "Register a new user", description = "Registration of a new user", tags = { "user" })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@NotNull @Valid @RequestBody UserDto userDto) {

        return userService.register(userDto);
    }

    @RolesAllowed("ADMIN")
    @Operation(summary = "Fetch a user by ID", description = "Fetch a user by ID", tags = { "user" })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto fetchUser(@PathVariable long id){
        return userService.findUserById(id);

    }

    @RolesAllowed("ADMIN")
    @Operation(summary = "Fetch a user by ID", description = "Fetch a user by ID", tags = { "user" })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable long userId, @NotNull @Valid @RequestBody UserDto userDto){
        return userService.updateUser(userId, userDto);

    }

    @RolesAllowed("ADMIN")
    @Operation(summary = "Delete a user by ID", description = "Delete a user by ID", tags = { "user" })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id){
         userService.deleteUser(id);

    }

}

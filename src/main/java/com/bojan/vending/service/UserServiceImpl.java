package com.bojan.vending.service;

import com.bojan.vending.dto.UserDto;
import com.bojan.vending.dto.mapper.UserMapper;
import com.bojan.vending.exception.EntityExistsException;
import com.bojan.vending.exception.EntityNotFoundException;
import com.bojan.vending.model.Role;
import com.bojan.vending.model.User;
import com.bojan.vending.repository.RoleRepository;
import com.bojan.vending.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Handles user registration
     *
     * @param userDto
     * @return the UserDto object
     */
    @Override
    public UserDto register(final UserDto userDto) {
        Optional<User> userOpt = userRepository.findByUsername(userDto.getUsername());
        if (userOpt.isPresent()) {
            throw new EntityExistsException(User.class, "username", userDto.getUsername());
        }
        if(userDto.getRoles().isEmpty()){
            Optional<Role> buyerRole = roleRepository.findByName("ROLE_BUYER");
            userDto.setRoles(Set.of(buyerRole.get()));
        }
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .deposit(userDto.getDeposit())
                .enabled(true)
                .roles(userDto.getRoles())
                .build();
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public UserDto findUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username:" + username));
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto findUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID:" + id));
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID:" + id));
        user = UserMapper.updateUser(user, userDto);
        userRepository.save(user);

        return UserMapper.toUserDto(user);

    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID:" + id));
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void createRole(String roleName) {
        Optional<Role> roleOptional = roleRepository.findByName(roleName);
        if(roleOptional.isPresent()){
            throw new EntityExistsException(User.class, "role", roleName);
        }
        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);
    }

    @Override
    public Role findRoleByName(String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with name:" + roleName));
        return role;
    }

}

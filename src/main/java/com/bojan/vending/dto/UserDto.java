package com.bojan.vending.dto;

import com.bojan.vending.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private Integer deposit;
    private Boolean enabled;
    private Set<Role> roles;

}

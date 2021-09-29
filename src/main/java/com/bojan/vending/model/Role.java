package com.bojan.vending.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
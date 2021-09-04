package com.example.techthink.config;

import com.example.techthink.persistence.model.Role;
import com.example.techthink.persistence.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;


public class UserPrincipal implements UserDetails {

    private final User userEntity;

    public UserPrincipal(User userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        Collection<Role> roles = userEntity.getRoles();

        if (roles == null) {
            return authorities;
        }

        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        });
        return authorities;
    }

    public Long getId() {
        return this.userEntity.getId();
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

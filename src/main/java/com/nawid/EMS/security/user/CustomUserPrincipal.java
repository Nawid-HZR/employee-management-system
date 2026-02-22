package com.nawid.EMS.security.user;




import org.jspecify.annotations.Nullable;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class CustomUserPrincipal implements UserDetails {

    private final Long userId;
    private final Long employeeId;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserPrincipal(
            Long userId,
            Long employeeId,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities) {

        this.userId = userId;
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }


    public Long getUserId() {
        return userId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }
}
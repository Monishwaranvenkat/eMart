package com.eMart.main.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private String userName;
    private String password;
    private boolean active;
    private boolean locked;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(AccountDetails accountDetails)
    {

        this.userName=accountDetails.employeeDetails.getEmail();
        this.password=accountDetails.getPassword();
        this.active=accountDetails.getIsactive()==1?true:false;
        this.locked=accountDetails.getIslocked()==1?true:false;
        this.authorities = Arrays.stream(accountDetails.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }
    public MyUserDetails()
    {
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}

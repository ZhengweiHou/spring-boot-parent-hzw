package com.hzw.learn.springboot.cas.client;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class HzwAuthenticationUserDetailsService implements AuthenticationUserDetailsService {
    public UserDetails loadUserDetails(Authentication authentication) throws UsernameNotFoundException {
        String username = (String) authentication.getPrincipal();

        List<SimpleGrantedAuthority> auths = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("role1");
        auths.add(role1);

        UserDetails ud = new User(username, "",auths);
        return ud;
    }
}

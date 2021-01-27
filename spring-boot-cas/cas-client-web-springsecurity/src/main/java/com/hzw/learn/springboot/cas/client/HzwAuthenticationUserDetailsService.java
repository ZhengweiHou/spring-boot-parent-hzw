package com.hzw.learn.springboot.cas.client;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class HzwAuthenticationUserDetailsService implements AuthenticationUserDetailsService {
    public UserDetails loadUserDetails(Authentication authentication) throws UsernameNotFoundException {
        return null;
    }
}

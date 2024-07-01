package com.myapp.security_user.service;

import com.myapp.security_user.domain.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private static UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUSer = userService.findByUsername(username);
        if (applicationUSer == null) {
            throw new UsernameNotFoundException("User with username " + username + " does not exists");
        }
        return User.withUsername(username)
                .password(applicationUSer.getPassword())
                .roles("USER")
                .disabled(false)
                .build();
    }
}

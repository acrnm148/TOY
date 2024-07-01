package com.myapp.security_user.service;

import com.myapp.security_user.domain.ApplicationUser;
import com.myapp.security_user.dto.UserDto;
import com.myapp.security_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private static UserRepository userRepository;
    private static PasswordEncoder passwordEncoder;

    @Override
    public ApplicationUser createUser(UserDto userDto) {
        ApplicationUser user = ApplicationUser.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .userName(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public ApplicationUser findByUsername(String username) {
        return null;
    }
}

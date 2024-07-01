package com.myapp.security_user.service;

import com.myapp.security_user.domain.ApplicationUser;
import com.myapp.security_user.dto.UserDto;

public interface UserService {
    ApplicationUser createUser(UserDto userDto);
    ApplicationUser findByUsername(String username);
}

package com.myapp.toyee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityConfig;

@Configuration
public class SecurityConfiguration extends SecurityConfig {

    public SecurityConfiguration(String config) {
        super(config);
    }


}

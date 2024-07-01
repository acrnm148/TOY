package com.myapp.security_user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfiguration {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 인증 없이 접근 가능한 엔드포인트
                .requestMatchers("/adduser", "/login", "/login-error").permitAll()
                .anyRequest().authenticated()
                // failureUrl : 로그인 실패 페이지 지정하면 로그인 실패 시 해당 페이지로 스프링시큐리티가 리다이렉트해줌.
                .and().formLogin().loginPage("/login").failureUrl("/login-error");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers("/webjars/**", "/images/*", "/css/*");
    }

    /**
     * 비밀번호 암호화
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

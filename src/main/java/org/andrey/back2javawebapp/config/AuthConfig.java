package org.andrey.back2javawebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class AuthConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .httpBasic().disable()
                .authorizeHttpRequests(c -> c
                .requestMatchers(HttpMethod.POST, "/register*").permitAll()
                .requestMatchers(HttpMethod.POST, "/error").permitAll()
                .anyRequest().authenticated());

        return http.build();
    }
}

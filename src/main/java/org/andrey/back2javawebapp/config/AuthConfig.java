package org.andrey.back2javawebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(c -> c
                .requestMatchers(HttpMethod.POST,"/register*").permitAll()
                .anyRequest().authenticated());

        return http.build();
    }
}

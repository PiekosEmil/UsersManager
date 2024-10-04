package com.emilpiekos.usersmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityController(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/script/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/register-success").permitAll()
                        .requestMatchers("/send-registration-form").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                        .ignoringRequestMatchers("/api/**"))
                .headers(header -> header.referrerPolicy(referrer -> referrer
                                .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN))
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .requestCache(RequestCacheConfigurer::disable);
        httpSecurity.formLogin(login -> login
                .loginPage("/login").permitAll());
        httpSecurity.logout(logout -> logout
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID"));
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

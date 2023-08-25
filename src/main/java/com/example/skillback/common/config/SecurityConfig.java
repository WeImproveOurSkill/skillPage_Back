package com.example.skillback.common.config;

import com.example.skillback.common.jwt.JwtUtil;

import com.example.skillback.common.security.JwtAuthFilter;
import com.example.skillback.common.security.UserDetailsImpl;
import com.example.skillback.common.security.UserDetailsServiceImpl;
import com.example.skillback.common.security.redis.refresh.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 지원을 가능하게 함
@RequiredArgsConstructor
public class SecurityConfig {

//    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final RedisService redisService;
//    @Bean
//    public SecurityFilterChain exceptionResources(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(resources ->
//                resources.requestMatchers("/templates/**").permitAll()
//                    .anyRequest().authenticated());
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable);
//            .cors(Customizer.withDefaults());

        http
            .authorizeHttpRequests(auth ->
                auth.requestMatchers("/**").permitAll()
                    .anyRequest().authenticated())
            .addFilterBefore(new JwtAuthFilter(userDetailsService, jwtUtil,redisService),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}

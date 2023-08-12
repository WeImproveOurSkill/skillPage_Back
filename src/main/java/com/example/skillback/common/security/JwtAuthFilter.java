package com.example.skillback.common.security;

import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.jwt.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveAccessToken(request);

        if (token != null) {
            if (!jwtUtil.validToken(token)) {
                jwtExceptionHandler(response, "Token Error", HttpStatus.UNAUTHORIZED.value());
                return;
            }
            Claims userInformation = jwtUtil.getUserInformation(token);
            String userIdentifier = userInformation.getSubject();
            setAuthentication(userIdentifier);
        }
        filterChain.doFilter(request, response);
    }

    private Authentication createAuthentication(String userIdentifier) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userIdentifier);
        return new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
    }

    private void setAuthentication(String userIdentifier) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = this.createAuthentication(userIdentifier);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    public void jwtExceptionHandler(HttpServletResponse response, String msg, int statusCode) {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        try {
            String json = new ObjectMapper().writeValueAsString(
                new StatusResponse(statusCode, msg));
            response.getWriter().write(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

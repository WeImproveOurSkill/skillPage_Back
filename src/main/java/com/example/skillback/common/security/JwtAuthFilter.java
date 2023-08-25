package com.example.skillback.common.security;

import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.jwt.JwtUtil;
import com.example.skillback.common.security.redis.refresh.service.RedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final RedisService redisService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        String accessToken = jwtUtil.resolveAccessToken(request);
        String refreshToken = jwtUtil.resolveRefreshToken(request);

        if (accessToken != null) {
            if (checkToken(accessToken)) {
                if (refreshToken != null) {
                    if (checkToken(refreshToken)) {
                        jwtExceptionHandler(response, "Token Error",
                            HttpStatus.UNAUTHORIZED.value());
                        return;
                    }
                    Claims userInformation = jwtUtil.getUserInformation(refreshToken);
                    String userIdentifier = userInformation.getSubject();
                    if (redisService.exists(userIdentifier)) {
                        addATKInHeader(response, userIdentifier);
                        addRTKInHeader(response, userIdentifier);
                        setAuthentication(userIdentifier);
                    }
                }

            } else {
                Claims info = jwtUtil.getUserInformation(accessToken);
                String username = info.getSubject();
                setAuthentication(username);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkToken(String accessToken) {
        return !jwtUtil.validToken(accessToken);
    }

    private void addRTKInHeader(HttpServletResponse response, String userIdentifier) {
        String newRefreshToken = jwtUtil.crateRefreshToken(userIdentifier,
            userDetailsService.RollLoadUserByUsername(userIdentifier));
        redisService.deleteValues(newRefreshToken);
        redisService.setValues(newRefreshToken,userIdentifier);
        response.addHeader(JwtUtil.REFRESH_HEADER, newRefreshToken);
    }

    private void addATKInHeader(HttpServletResponse response, String userIdentifier) {
        String newAccessToken = jwtUtil.createAccessToken(userIdentifier,
            userDetailsService.RollLoadUserByUsername(userIdentifier));
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, newAccessToken);
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

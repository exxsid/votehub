package com.lacortezdev.onlinevotingsystem.user;

import com.lacortezdev.onlinevotingsystem.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserActiveFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public UserActiveFilter(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.split(" ")[1];
            // the username is the user id
            String username = jwtService.extractUsername(token);

            if (username == null) {
                throw new Exception();
            }

            User user = this.userService.loadUserByUsername(username);

            if (user == null) {
                throw new Exception();
            }

            if (!user.isActive()) {
                throw new Exception();
            }

        } catch (Exception e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        filterChain.doFilter(request, response);
    }
}

package com.edu.miu.backend.filter;


import com.edu.miu.backend.jwt.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserDetailsService userService;

    public JwtRequestFilter(JWTUtil jwtUtil, UserDetailsService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(token);
            if (username != null) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (jwtUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    usernamePasswordAuthToken
                            .setDetails(
                                    new WebAuthenticationDetailsSource()
                                            .buildDetails(request)
                            );
                    SecurityContextHolder.getContext().setAuthentication(
                            usernamePasswordAuthToken
                    );
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}

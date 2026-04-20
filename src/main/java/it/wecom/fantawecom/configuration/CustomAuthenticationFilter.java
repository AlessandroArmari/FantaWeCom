package it.wecom.fantawecom.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearer = request.getHeader(AUTHORIZATION);

        if (bearer == null || !bearer.startsWith("Bearer ")) {
            throw new AuthenticationException("Bearer token is required");
        }

        filterChain.doFilter(request, response);
    }
}

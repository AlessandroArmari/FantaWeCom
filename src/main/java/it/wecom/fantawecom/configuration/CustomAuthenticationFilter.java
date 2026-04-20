package it.wecom.fantawecom.configuration;

import it.wecom.fantawecom.configuration.exception.Exc404;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearer = request.getHeader(AUTHORIZATION);

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[1];

        if (bearer == null || !bearer.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            //QUI DEOVREI AGGIUNGERE
            //@Component
            //public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint

        }

        filterChain.doFilter(request, response);
    }
}

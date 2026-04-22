package it.wecom.fantawecom.configuration;

import it.wecom.fantawecom.K.Ksecurity;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtConverter jwtConverter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {


        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtConverter))
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );


        http.addFilterBefore(new CustomAuthenticationFilter(), BasicAuthenticationFilter.class);
        //.requestMatchers("/userKeycloak/test")
        //.hasRole("ADMIN")
        //.anyRequest().authenticated()
        //.requestMatchers()
        //);

        return http.build();
    }

    @Bean("keycloak")
    public Keycloak keycloak() {

        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:7080")
                .realm(Ksecurity.KEYCLOAK_REALM)
                .clientId(Ksecurity.KEYCLOAK_CLIENT_ID)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientSecret("hxed8N7IjxdtD49zIqa6iGtZyHa6w5pc")
                .build();

    }
}

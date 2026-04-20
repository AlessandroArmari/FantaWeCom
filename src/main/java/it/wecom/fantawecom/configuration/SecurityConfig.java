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
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll());
                //.requestMatchers("/userKeycloak/test")
                //.hasRole("ADMIN")
                //.anyRequest().authenticated()
                //.requestMatchers()
                //);

        return http.build();
    }

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:7080/")
                .realm(Ksecurity.KEYCLOAK_REALM)
                .clientId(Ksecurity.KEYCLOAK_CLIENT_ID)
                .grantType(OAuth2Constants.PASSWORD)
                .username(Ksecurity.KEYCLOAK_USERNAME)
                .password(Ksecurity.KEYCLOAK_PASSWORD)
                .build();
    }
}

package it.wecom.fantawecom.service;

import it.wecom.fantawecom.K.Ksecurity;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final Keycloak keycloak;

    public Object findByUsername(String username) {

        try {
            TokenManager tokenManager = keycloak.tokenManager();
            AccessTokenResponse accessTokenResponse = keycloak.tokenManager().getAccessToken();
            System.out.println("ok");
        } catch (BadRequestException ex) {
            System.out.println(ex.getResponse().readEntity(String.class));
        }


        return keycloak.realm(Ksecurity.KEYCLOAK_REALM)
                .users()
                .searchByUsername(username, true)
                .getFirst();
    }
}

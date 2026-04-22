package it.wecom.fantawecom.service;

import it.wecom.fantawecom.K.Ksecurity;
import it.wecom.fantawecom.configuration.exception.Exc404;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final Keycloak keycloak;

    public Object findByUsername(String username) {


        System.out.println("findByUsername");
        /*
        try {
            TokenManager tokenManager = keycloak.tokenManager();
            AccessTokenResponse accessTokenResponse = keycloak.tokenManager().getAccessToken();
            System.out.println("ok");
        } catch (BadRequestException ex) {
            System.out.println(ex.getResponse().readEntity(String.class));
        }
         */

        //AccessTokenResponse accessTokenResponse = keycloak.tokenManager().getAccessToken();


        return keycloak.realm(Ksecurity.KEYCLOAK_REALM)
                .users()
                .searchByUsername(username, true)
                .stream()
                .findFirst()
                .orElseThrow(() -> new Exc404(
                        String.format("User: %s not found", username),
                        String.format("User: %s not found", username),
                        this.getClass().getSimpleName(),
                        "findByUsername",
                        String.valueOf(999)));


    }
}

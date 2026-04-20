package it.wecom.fantawecom.service;

import it.wecom.fantawecom.K.Ksecurity;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final Keycloak keycloak;

    public UserRepresentation findByUsername(String username) {
        return keycloak.realm(Ksecurity.KEYCLOAK_REALM)
                .users()
                .searchByUsername(username, true)
                .getFirst();
    }
}

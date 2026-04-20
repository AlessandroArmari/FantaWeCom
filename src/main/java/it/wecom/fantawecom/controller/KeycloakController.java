package it.wecom.fantawecom.controller;

import it.wecom.fantawecom.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userKeycloak")
public class KeycloakController {

    private final KeycloakService keycloakService;

    @GetMapping("/test")
    public Object findUserByUsername(@RequestParam String username) {
        return keycloakService.findByUsername(username);
    }
}

package it.wecom.fantawecom.configuration;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Value("${jwt.auth.converter.principle-attribute}")
    private String principleAttribute;

    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {

        Collection<GrantedAuthority> scopes = jwtGrantedAuthoritiesConverter.convert(jwt);

        Collection<? extends GrantedAuthority> roles = this.extractResourceRoles(jwt);

        Collection<GrantedAuthority> authorities = Stream.concat(
                        scopes.stream(),
                        roles.stream()
                )
                .collect(Collectors.toSet());

        //CREATE TOKEN

        return new JwtAuthenticationToken(
                jwt,
                authorities,
                this.getPrincipleClaimName(jwt)
        );
    }

    private String getPrincipleClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (principleAttribute != null) {
            claimName = principleAttribute;
        }

        return jwt.getClaim(claimName);
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {

        Map<String, Object> realmAccess;
        //Map<String, Object> resource;
        Collection<String> resourceRoles;

        if (jwt.getClaim("realm_access") == null) {
            return Set.of();
        }

        realmAccess = jwt.getClaim("realm_access");

        if (realmAccess.get(resourceId) == null) {
            return Set.of();
        }

        resourceRoles = (Collection<String>) realmAccess.get(resourceId);

        Collection<? extends GrantedAuthority> grandetedAuthorities = resourceRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());

        return grandetedAuthorities;
    }
}

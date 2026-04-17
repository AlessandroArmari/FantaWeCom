package it.wecom.fantawecom.entity;

import it.wecom.fantawecom.K.Kentity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_keycloak")
@SQLDelete(sql = "UPDATE user_keycloak SET is_active = FALSE, deleted_at = LOCALTIMESTAMP WHERE id = ?")
@SQLRestriction(Kentity.IS_ACTIVE + Kentity.EQUALS_TRUE)
public class UserKeycloak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

}

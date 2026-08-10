package com.juari.store.model.entity;

import com.juari.store.model.enums.AuthenticationProvider;
import com.juari.store.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true
            , length = 50)
    private String username;

    @Column(nullable = false, unique = true
            , length = 100)
    private String email;

    @Column(length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "authentication_provider",
            nullable = false
    )
    @Builder.Default
    private AuthenticationProvider authenticationProvider =
            AuthenticationProvider.LOCAL;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false
    )
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;
}

package com.rdlab.education.domain.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "register_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String password;
    private String otpCode;
    private Integer registrationTryCount = 0;
    private Boolean verified = false;
    private Instant otpExpiryDate = Instant.now().plusSeconds(3600);
}

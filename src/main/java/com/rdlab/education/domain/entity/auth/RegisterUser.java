package com.rdlab.education.domain.entity.auth;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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

    @Type(JsonType.class)
    @Column(name = "category_id_list", columnDefinition = "jsonb")
    private Set<Long> categoryIdList = new HashSet<>();
}

package com.legalDigital.education.domain.entity.auth;

import com.legalDigital.education.domain.entity.edu.UserCourse;
import com.legalDigital.education.domain.entity.image.Base64Images;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "username")
    String username;
    @Column(name = "name")
    String name;
    @Column(name = "lastname")
    String lastname;
    @Column(name = "password")
    String password;
    @Column(name = "role")
    String role;
    @Column(name = "enabled")
    Boolean enabled;
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "updated_at")
    LocalDateTime updatedAt;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Base64Images image;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_details")
//    UserDetails userDetails;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + maskPassword() + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    private String maskPassword(){
        return "********";
    }

    @OneToMany(mappedBy = "user")
    private Collection<UserCourse> userCourse;

}

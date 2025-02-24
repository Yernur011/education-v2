package com.rdlab.education.domain.entity.auth;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "register_user")
public class RegisterUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String otpCode;
    private Integer registrationTryCount = 0;
    private Boolean verified = false;
    private Instant otpExpiryDate = Instant.now().plusSeconds(3600);

    public RegisterUser(){

    }
    public RegisterUser(Long id, String username, String password, String otpCode, Integer registrationTryCount, Boolean verified, Instant otpExpiryDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.otpCode = otpCode;
        this.registrationTryCount = registrationTryCount;
        this.verified = verified;
        this.otpExpiryDate = otpExpiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public Integer getRegistrationTryCount() {
        return registrationTryCount;
    }

    public void setRegistrationTryCount(Integer registrationTryCount) {
        this.registrationTryCount = registrationTryCount;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Instant getOtpExpiryDate() {
        return otpExpiryDate;
    }

    public void setOtpExpiryDate(Instant otpExpiryDate) {
        this.otpExpiryDate = otpExpiryDate;
    }

}

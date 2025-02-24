package com.rdlab.education.domain.dto.auth;

public class RefreshToken extends TokenDetails {
    String nonce;
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}

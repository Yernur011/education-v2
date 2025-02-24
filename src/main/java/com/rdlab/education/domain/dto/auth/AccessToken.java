package com.rdlab.education.domain.dto.auth;

import java.util.ArrayList;
import java.util.List;

public class AccessToken extends TokenDetails {
    String username;
    List<String> role = new ArrayList<>();
    String audience;
    List<String> scopes = new ArrayList<>();

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }
}

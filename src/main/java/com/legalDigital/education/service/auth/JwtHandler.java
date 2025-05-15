package com.legalDigital.education.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.legalDigital.education.domain.dto.auth.AccessToken;
import com.legalDigital.education.domain.dto.auth.RefreshToken;
import com.legalDigital.education.domain.exceptions.auth.AuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;


@Component
public class JwtHandler {
    @Value("${jwt.secret}")
    private String secret;

    public String extractUsername(String token) {
        ObjectMapper objectMapper = new ObjectMapper();

        var tokenDetails = getTokenDetails(token);

        if ("ACCESS_TOKEN".equals(tokenDetails.get("type"))){
            AccessToken accessToken = objectMapper.convertValue(tokenDetails, AccessToken.class);
            return Try.of(() -> accessToken)
                    .mapTry(AccessToken::getUsername)
                    .getOrElseThrow(ex -> new AuthException("Invalid token ", ex));
        } else if ("REFRESH_TOKEN".equals(tokenDetails.get("type"))) {
            RefreshToken refreshToken = objectMapper.convertValue(tokenDetails, RefreshToken.class);
            return Try.of(() -> refreshToken)
                    .mapTry(RefreshToken::getUsername)
                    .getOrElseThrow(ex -> new AuthException("Invalid token ", ex));
        } else {
            throw new AuthException("Invalid token");
        }
    }

    public String extractTokenType(String token) {
        Map tokenDetails = getTokenDetails(token);
        return tokenDetails.get("type").toString();
    }
    private Map getTokenDetails(String token) {
        VerificationResult verificationResult = validateToken(token);
        return verificationResult.getClaims().get("token", Map.class);
    }

    public VerificationResult validateToken(String token) {
        return verify(token);
    }

    public boolean isRefreshTokenValid(String token) {
        return verify(token) != null && "REFRESH_TOKEN".equals(getTokenDetails(token).get("type"));
    }
    public VerificationResult verify(String token){
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();

        if (expiration.before(Date.from(Instant.now()))) {

            throw new AuthException("Token expired");
        }
        return new VerificationResult(token, claims);
    }

    private Claims getClaimsFromToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getEncoder().encode(secret.getBytes()));
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static class VerificationResult{
        String token;
        Claims claims;
        public VerificationResult(String token, Claims claims) {
            this.token = token;
            this.claims = claims;
        }
        public Claims getClaims() {
            return claims;
        }
    }
}

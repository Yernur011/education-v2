package com.rdlab.education.service.auth;

import com.rdlab.education.domain.dto.auth.*;
import com.rdlab.education.domain.entity.auth.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class TokenFactoryService {
    private static final Logger log = LoggerFactory.getLogger(TokenFactoryService.class);
    private final JwtHandler jwtHandler;
    private final UserService userService;
    @Value("${jwt.secret}")
    String secret;

    @Value("${jwt.token.refresh.expiration}")
    Long expirationInDays;


    @Value("${jwt.token.issuer}")
    String issuer;

    @Value("${jwt.token.access.expiration}")
    Integer expirationInSeconds;

    public TokenFactoryService(JwtHandler jwtHandler, UserService userService) {
        this.jwtHandler = jwtHandler;
        this.userService = userService;
    }


    public Tokens generateToken(Authentication authentication) {
        Instant createdAt = Instant.now();
        Instant accessTokenExpiredAt = createdAt.plusSeconds(expirationInSeconds);
        Instant refreshTokenExpiredAt = createdAt.plus(expirationInDays, TimeUnit.DAYS.toChronoUnit());

        RefreshToken refreshToken = refreshToken(authentication);
        AccessToken accessToken = getAccessToken(authentication);


        var stringAccessToken = generateToken(accessTokenExpiredAt, getClaims(accessToken),
                authentication.getName());
        var stringRefreshToken = generateToken(refreshTokenExpiredAt, getClaims(refreshToken),
                authentication.getName());

        return new Tokens(stringAccessToken, createdAt.plusSeconds(expirationInSeconds*5L).toString(),
                accessTokenExpiredAt.plusSeconds(expirationInSeconds*5L).toString(),
                stringRefreshToken,
                createdAt.plusSeconds(expirationInSeconds*5L).toString(),
                refreshTokenExpiredAt.plusSeconds(expirationInSeconds*5L).toString());
    }

    private Map<String, Object> getClaims(TokenDetails tokenDetails) {
        return Map.of("token", tokenDetails);
    }

    private RefreshToken refreshToken(Authentication authentication) {

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setType("REFRESH_TOKEN");
        refreshToken.setNonce("refresh"); //todo вывести в application.yaml
        refreshToken.setUsername(authentication.getName());
        tokenBuilder(Instant.now().plusSeconds(expirationInSeconds*5L),
                Instant.now().plusSeconds(expirationInSeconds*5L).plus(expirationInDays, TimeUnit.DAYS.toChronoUnit()),
                refreshToken);
        return refreshToken;
    }

    private AccessToken getAccessToken(Authentication authentication) {

        AccessToken accessToken = new AccessToken();
        accessToken.setAudience("USER_APPLICATION");//todo вывести в enum

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(grantedAuthority ->
                accessToken.getRole().add(grantedAuthority.getAuthority()));
        accessToken.setUsername(authentication.getName());
        accessToken.setType("ACCESS_TOKEN");
        tokenBuilder(Instant.now().plusSeconds(expirationInSeconds*5L),
                Instant.now().plusSeconds(expirationInSeconds*5L).plusSeconds(expirationInSeconds),
                accessToken);

        return accessToken;
    }

    private AccessToken getAccessToken(Users authentication) {

        AccessToken accessToken = new AccessToken();
        accessToken.setAudience("USER_APPLICATION");//todo вывести в enum

        var authorities = authentication.getRole();
        accessToken.getRole().add(authorities);
        accessToken.setUsername(authentication.getUsername());
        accessToken.setType("ACCESS_TOKEN");
        tokenBuilder(Instant.now().plusSeconds(expirationInSeconds*5L), Instant.now().plusSeconds(expirationInSeconds*5L).plusSeconds(expirationInSeconds), accessToken);

        return accessToken;
    }
    public Token refreshAccessToken(String refreshToken) {
        if (!jwtHandler.isRefreshTokenValid(refreshToken)) {
            throw new BadCredentialsException("Invalid refresh token");
        }
        String username = jwtHandler.extractUsername(refreshToken);

        UserDetails userDetails = userService.loadUserByUsername(username);
        String accessToken = generateAccessToken(((CustomUserDetails) userDetails).user());
        var claims = jwtHandler.validateToken(refreshToken).getClaims();

        return new Token(accessToken, "Bearer", Instant.now().plusSeconds(expirationInSeconds*5L).toString()
                ,Instant.now().plusSeconds(expirationInSeconds*5L).plusSeconds(expirationInSeconds).toString());
    }

    private synchronized void tokenBuilder(Instant createdAt, Instant expiredAt, TokenDetails accessToken) {
        accessToken.setIssuedAt(createdAt);
        accessToken.setExpiresAt(expiredAt);
    }

    private String generateToken(Instant expirationDate, Map<String, Object> claims, String subject){

        Date createDate = Date.from(Instant.now());
        Date expiredAt = Date.from(expirationDate);
        SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getEncoder().encode(secret.getBytes()));

        return Jwts.builder()
                .claims(claims)
                .issuer(issuer)
                .subject(subject)
                .issuedAt(createDate)
                .expiration(expiredAt)
                .id(UUID.randomUUID().toString())
                .signWith(secretKey)
                .compact();
    }
    public String generateAccessToken(Users authentication) {
        AccessToken accessToken = getAccessToken(authentication);
        return generateToken(Instant.now().plusSeconds(expirationInSeconds),
                getClaims(accessToken), authentication.getUsername());
    }
}

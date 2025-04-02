package com.rdlab.education.config.security;


import com.rdlab.education.domain.enums.UserRole;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.rdlab.education.utils.codes.ProductCode.ADMIN_URI;
import static com.rdlab.education.utils.codes.ProductCode.V1_URI;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    private final String PREFIX = "ROLE_";
    private final String[] publicRouts = {
            "/api/v1/auth/register",
            "/api/v1/auth/verify",
            "/api/v1/auth/login",
            "/api/v1/auth/refresh",
            "/v3/api-docs",
            "/swagger-ui/**",
            "/v3/api-docs/swagger-config",
            "/api/v1/main-info/**"
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final RefreshTokenFilter refreshTokenFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,
                                 RefreshTokenFilter refreshTokenFilter,
                                 CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                                 CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.refreshTokenFilter = refreshTokenFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, @Qualifier("usernamePasswordAuthenticationManager") AuthenticationManager authenticationManager) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(httpReq -> {
                    httpReq.requestMatchers(HttpMethod.OPTIONS)
                            .permitAll()
                            .requestMatchers(publicRouts)
                            .permitAll()
                            .requestMatchers(V1_URI + ADMIN_URI + "/**")
                            .hasAuthority(UserRole.ADMIN.getRole())
                            .anyRequest()
                            .authenticated();
                })
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(customAuthenticationEntryPoint);
                    exception.accessDeniedHandler(customAccessDeniedHandler);
                })
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationManager(authenticationManager);


        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(refreshTokenFilter, JwtAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Можно заменить на конкретные домены
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

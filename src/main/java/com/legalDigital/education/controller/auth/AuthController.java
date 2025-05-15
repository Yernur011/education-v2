package com.legalDigital.education.controller.auth;

import com.legalDigital.education.domain.dto.auth.LoginRequest;
import com.legalDigital.education.domain.dto.auth.RegistrationDto;
import com.legalDigital.education.domain.dto.auth.RegistrationResponse;
import com.legalDigital.education.domain.dto.auth.Tokens;
import com.legalDigital.education.domain.dto.auth.VerifyOtpRequest;
import com.legalDigital.education.service.auth.RegistrationService;
import com.legalDigital.education.service.auth.UsernamePasswordAuthenticationManager;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UsernamePasswordAuthenticationManager authenticationService;

    public AuthController(RegistrationService registrationService, UsernamePasswordAuthenticationManager authenticationService) {
        this.registrationService = registrationService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Tokens> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.signIn(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationDto registrationDto) {
        return ResponseEntity.ok(registrationService.register(registrationDto));
    }
    @PostMapping("/verify")
    public ResponseEntity<RegistrationResponse> verifyRegistration(@Valid @RequestBody VerifyOtpRequest verifyOtpRequest) {
        return ResponseEntity.ok(registrationService.verifyOtpCode(verifyOtpRequest));
    }
    @PostMapping("/profile")
    public ResponseEntity<Object> test() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}

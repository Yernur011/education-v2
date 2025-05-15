package com.legalDigital.education.service.otp;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {
    private static final int OTP_LENGTH = 6;
    public String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
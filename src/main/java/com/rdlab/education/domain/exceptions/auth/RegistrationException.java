package com.rdlab.education.domain.exceptions.auth;


import com.rdlab.education.domain.exceptions.ApiException;

public class RegistrationException extends ApiException {
    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}

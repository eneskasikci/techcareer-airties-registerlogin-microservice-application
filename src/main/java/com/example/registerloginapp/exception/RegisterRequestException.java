package com.example.registerloginapp.exception;

import com.example.registerloginapp.repository.UserRepository;

public class RegisterRequestException extends Exception {
    public RegisterRequestException(String message) {
        super(message);
    }

}

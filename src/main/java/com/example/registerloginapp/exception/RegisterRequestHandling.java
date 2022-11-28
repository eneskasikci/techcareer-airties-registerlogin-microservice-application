package com.example.registerloginapp.exception;

import com.example.registerloginapp.payload.request.SignupRequest;
import com.example.registerloginapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class RegisterRequestHandling {

    @Autowired
    UserRepository userRepository;

    @ExceptionHandler({RegisterRequestException.class})
    public String checkRequestRequirements(SignupRequest signupRequest) {
        if (signupRequest.getPassword().length() < 6 || signupRequest.getPassword().length() > 40) {
            return "Password must be between 6 and 40 characters";
        }if (signupRequest.getEmail().length() > 50){
            return "Email must be less than 50 characters";
        }if(signupRequest.getUsername().length() > 20){
            return "Username must be less than 20 characters";
        }if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return "Username is already taken!";
        }if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return "Email is already in use!";
        }else {
            return null;
        }
    }
}

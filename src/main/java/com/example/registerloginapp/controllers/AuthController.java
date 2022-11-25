package com.example.registerloginapp.controllers;

import com.example.registerloginapp.exception.RegisterRequestException;
import com.example.registerloginapp.models.User;
import com.example.registerloginapp.payload.request.LoginRequest;
import com.example.registerloginapp.payload.request.SignupRequest;
import com.example.registerloginapp.services.RegisterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    RegisterLoginService registerLoginService;

    public AuthController(RegisterLoginService registerLoginService) {
        this.registerLoginService = registerLoginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return registerLoginService.authenticateUser(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws RegisterRequestException {
        return registerLoginService.registerUser(signUpRequest);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return registerLoginService.getAllUsers();
    }

    @DeleteMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEveryUser() {
        registerLoginService.deleteEveryUser();
    }
}



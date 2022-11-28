package com.example.registerloginapp.controllers;

import com.example.registerloginapp.exception.RegisterRequestException;
import com.example.registerloginapp.models.User;
import com.example.registerloginapp.payload.request.LoginRequest;
import com.example.registerloginapp.payload.request.SignupRequest;
import com.example.registerloginapp.services.RegisterLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "Authentication", description = "Authentication API for the Register Login App")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    RegisterLoginService registerLoginService;

    public AuthController(RegisterLoginService registerLoginService) {
        this.registerLoginService = registerLoginService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login operation for the Register Login App", notes = "We are " +
            "requesting the username and password for the user in order to login. Then we are generating a JWT Bearer Token.",response = User.class)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody @ApiParam(value="Requirements for the login request. We are using a DTO.") LoginRequest loginRequest) {
        return registerLoginService.authenticateUser(loginRequest);
    }

    @PostMapping("/register")
    @ApiOperation(value = "Register operation for the Register Login App",
            notes = "We are requesting the username, email and password for the user " +
                    "in order to register. The Role is not neccessary. If there is not a role provided, the Role of the " +
                    "Registered user is set to USER by default.", response = User.class)
    public ResponseEntity<?> registerUser(@Valid @RequestBody @ApiParam(value = "Requirements for the register request. We are using a DTO.") SignupRequest signUpRequest) throws RegisterRequestException {
        return registerLoginService.registerUser(signUpRequest);
    }

    @GetMapping("/users")
    @ApiOperation(value = "Get all users operation for the Register Login App",
            notes = "In order to successfully make this request, the logged in user should have a Admin role.", response = User.class)
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return registerLoginService.getAllUsers();
    }

    @DeleteMapping("/users")
    @ApiOperation(value = "Delete all users operation for the Register Login App",
            notes = "In order to successfully make this request, the logged in user should have a Admin role.", response = User.class)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEveryUser() {
        registerLoginService.deleteEveryUser();
    }
}



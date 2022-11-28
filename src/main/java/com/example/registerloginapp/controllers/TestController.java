package com.example.registerloginapp.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(tags = "Authorization Tests", description = "Authorization Tests for the Register Login App")
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    @ApiOperation(value = "All content.",
            notes = "This returns a string Public Content, without need for login.")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @ApiOperation(value = "User content.",
            notes = "This returns a string User Content, only for logged in users." +
                    "Logged in users can access this endpoint.")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @ApiOperation(value = "Moderator content.",
            notes = "This returns a string Moderator Board, only for logged in users with the role of MODERATOR." +
                    "Logged in users with the role of MODERATOR can access this endpoint." +
                    "If it's not, the request is null.")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @ApiOperation(value = "Admin content.",
            notes = "This returns a string Admin Board, only for logged in users with the role of ADMIN." +
                    "Logged in users with the role of ADMIN can access this endpoint." +
                    "If it's not, the request is null.")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}

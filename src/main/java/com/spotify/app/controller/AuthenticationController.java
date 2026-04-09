package com.spotify.app.controller;

import com.spotify.app.models.LoginResponsePackage.LoginResponse;
import com.spotify.app.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;

    public AuthenticationController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/jwt-token/{userId}")
    public ResponseEntity<LoginResponse> authenticate(@PathVariable String userId) {
        String jwtToken = jwtService.generateToken(userId);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}

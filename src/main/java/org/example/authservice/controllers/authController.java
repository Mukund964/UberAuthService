package org.example.authservice.controllers;

import org.example.authservice.Services.AuthService;
import org.example.authservice.dtos.passengerDto;
import org.example.authservice.dtos.passengerSignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup/passenger")
    public ResponseEntity<passengerDto> signupPassenger(@RequestBody passengerSignUpRequestDto passengerSignUpDto) {
        passengerDto passenger = authService.signupPassenger(passengerSignUpDto);
        return ResponseEntity.ok(passenger);
    }
}

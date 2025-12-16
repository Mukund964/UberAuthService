package org.example.authservice.controllers;

import org.example.authservice.dtos.passengerDto;
import org.example.authservice.dtos.passengerSignUpRequestDto;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {

    @PostMapping("/signup/passenger")
    public RequestEntity<passengerDto> signupPassenger(@RequestBody passengerSignUpRequestDto passengerSignUpDto) {
            return null;
    }
}

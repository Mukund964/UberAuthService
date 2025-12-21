package org.example.authservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.authservice.Services.AuthService;
import org.example.authservice.Services.JwtService;
import org.example.authservice.dtos.passengerDto;
import org.example.authservice.dtos.passengerSignInRequestDto;
import org.example.authservice.dtos.passengerSignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {
    @Value("${cookie.expiry}")
    private Long cookieExpiry;
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup/passenger")
    public ResponseEntity<?> signupPassenger(@RequestBody passengerSignUpRequestDto passengerSignUpDto) {
        passengerDto passenger = authService.signupPassenger(passengerSignUpDto);
        if(passenger == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(passenger);
    }

    @PostMapping("/signIn/passenger")
    public ResponseEntity<?> signInPassenger(@RequestBody passengerSignInRequestDto passengerSignInDto, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(passengerSignInDto.getEmail(), passengerSignInDto.getPassword()));
        if(authentication.isAuthenticated()){
            String jwtToken = jwtService.tokenCreationWithoutMap(passengerSignInDto.getEmail());
            ResponseCookie cookie = ResponseCookie.from("JWT", jwtToken)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(cookieExpiry)
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.ok(jwtToken);

        }else{
            return ResponseEntity.badRequest().build();

        }
    }
}

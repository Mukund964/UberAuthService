package org.example.authservice.Services;

import org.example.authservice.Models.passenger;
import org.example.authservice.Repository.PassengerRepository;
import org.example.authservice.dtos.passengerDto;
import org.example.authservice.dtos.passengerSignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private PassengerRepository passengerRepository;

    public passengerDto signupPassenger(passengerSignUpRequestDto passengerSignUpDto){
        passenger Passenger = passenger.builder()
                .email(passengerSignUpDto.getEmail())
                .Name(passengerSignUpDto.getName())
                .password(passengerSignUpDto.getPassword())
                .phoneNumber(passengerSignUpDto.getPhoneNumber())
                .build();

        return passengerDto.from(Passenger);
    }
}

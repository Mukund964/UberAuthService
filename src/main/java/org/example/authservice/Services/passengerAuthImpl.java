package org.example.authservice.Services;

import org.example.authservice.Models.passenger;
import org.example.authservice.Repository.PassengerRepository;
import org.example.authservice.helpers.passengerAuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class passengerAuthImpl implements UserDetailsService {
    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<passenger> passenger = passengerRepository.findByEmail(email);
        if(passenger.isPresent()){
            return new passengerAuthDetails(passenger.get());
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}

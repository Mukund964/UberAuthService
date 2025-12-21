package org.example.authservice.Repository;

import org.example.authservice.Models.passenger;
import org.example.authservice.dtos.passengerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public interface PassengerRepository extends JpaRepository<passenger,Long> {
    Optional<passenger> findByEmail(String email);
}

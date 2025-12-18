package org.example.authservice.dtos;

import lombok.*;
import org.example.authservice.Models.passenger;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class passengerDto {
    private String id;

    private String name;

    private String email;

    private String password; // encrypted password

    private String phoneNumber;

    private LocalDateTime createdAt;

    public static passengerDto from(passenger p) {
        return passengerDto.builder()
                .id(String.valueOf(((long) p.getId())))
                .email(p.getEmail())
                .name(p.getName())
                .password(p.getPassword())
                .phoneNumber(p.getPhoneNumber())
                .createdAt(p.getCreatedAt())
                .build();

    }
}

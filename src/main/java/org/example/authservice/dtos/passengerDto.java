package org.example.authservice.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class passengerDto {
    private String id;

    private String name;

    private String email;

    private String password; // encrypted password

    private String phoneNumber;

    private LocalDateTime createdAt;
}

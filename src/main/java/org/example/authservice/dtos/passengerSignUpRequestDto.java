package org.example.authservice.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class passengerSignUpRequestDto {
    private String email;

    private String password;

    private String phoneNumber;

    private String name;
}

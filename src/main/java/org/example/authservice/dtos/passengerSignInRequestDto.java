package org.example.authservice.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class passengerSignInRequestDto {
    private String Email;
    private String Password;

}

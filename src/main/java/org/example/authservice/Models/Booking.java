package org.example.authservice.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel {
    @Enumerated(EnumType.STRING)
    private BookingStatus BookingStatus;


    private LocalDateTime StartTime;
    private LocalDateTime EndTime;

    private Long TotalDistance;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private passenger passenger;

}

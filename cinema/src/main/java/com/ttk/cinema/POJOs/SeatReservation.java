package com.ttk.cinema.POJOs;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class SeatReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long reservationId;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    Seat seat;

    @ManyToOne
    @JoinColumn(name = "show_id")
    ShowEvent showEvent;

    String status; // BOOKED, AVAILABLE
}

package com.ttk.cinema.POJOs;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long ticketId;

    float ticketPrice;
    LocalDateTime createdDate;
    String status; // PENDING, PAID, CANCELLED
    String bookingType; // PRE-BOOKED, WALK-IN

    @ManyToOne
    @JoinColumn(name = "show_id")
    ShowEvent show;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    Seat seat;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    User customer;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    User staff;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;
}

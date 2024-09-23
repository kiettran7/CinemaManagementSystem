package com.ttk.cinema.POJOs;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long seatId;

    String seatName;

    @ManyToOne
    @JoinColumn(name = "show_room_id")
    ShowRoom showRoom;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL)
    Set<SeatReservation> seatReservations;
}

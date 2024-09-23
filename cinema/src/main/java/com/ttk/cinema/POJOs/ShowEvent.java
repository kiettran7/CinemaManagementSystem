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
public class ShowEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long showId;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    Showtime showtime;

    @ManyToOne
    @JoinColumn(name = "show_room_id")
    ShowRoom showRoom;
}

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
public class ShowRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long showRoomId;

    String showRoomName;

    @OneToMany(mappedBy = "showRoom", cascade = CascadeType.ALL)
    Set<ShowEvent> shows;

    @OneToMany(mappedBy = "showRoom", cascade = CascadeType.ALL)
    Set<Seat> seats;
}

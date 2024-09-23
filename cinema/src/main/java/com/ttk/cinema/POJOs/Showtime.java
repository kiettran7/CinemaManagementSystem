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
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long showtimeId;

    String startTime;
    String endTime;

    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
    Set<ShowEvent> showEvents;
}

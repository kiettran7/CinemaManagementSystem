package com.ttk.cinema.POJOs;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ShowSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long showScheduleId;
    LocalDate showDate;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    Movie movie;
}


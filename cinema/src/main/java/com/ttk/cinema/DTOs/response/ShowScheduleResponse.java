package com.ttk.cinema.DTOs.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowScheduleResponse {
    Long showScheduleId;
    LocalDate showDate;
    MovieResponse movieId; // Liên kết với movie
}

package com.ttk.cinema.DTOs.request.creation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowScheduleCreationRequest {
    LocalDate showDate;
    Long movieId; // Liên kết với bảng movie
}

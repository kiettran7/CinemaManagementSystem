package com.ttk.cinema.DTOs.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowScheduleUpdateRequest {
    LocalDate showDate; // Cập nhật ngày chiếu
    Long movieId; // Liên kết với bảng movie, có thể thay đổi
}

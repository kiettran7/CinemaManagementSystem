package com.ttk.cinema.DTOs.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeUpdateRequest {
    String startTime; // Cập nhật thời gian bắt đầu
    String endTime;   // Cập nhật thời gian kết thúc
}
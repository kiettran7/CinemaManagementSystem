package com.ttk.cinema.DTOs.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatUpdateRequest {
    String seatName; // Cập nhật tên ghế
    Long showRoomId; // Liên kết với bảng show_room, có thể thay đổi
}
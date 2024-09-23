package com.ttk.cinema.DTOs.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatResponse {
    Long seatId;
    String seatName;
    ShowRoomResponse showRoomId; // Liên kết với show_room
}

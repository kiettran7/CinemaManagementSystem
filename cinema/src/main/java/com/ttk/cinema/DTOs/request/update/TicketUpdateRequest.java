package com.ttk.cinema.DTOs.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketUpdateRequest {
    float ticketPrice; // Cập nhật giá vé
    String status; // Cập nhật trạng thái
    String bookingType; // Cập nhật loại đặt chỗ
    Long showId;    // Liên kết với bảng show_event
    Long seatId;    // Liên kết với bảng seat
    Long customerId; // Liên kết với bảng user, có thể thay đổi
    Long staffId;    // Liên kết với bảng user, có thể thay đổi
    Long movieId;    // Liên kết với bảng movie, có thể thay đổi
}

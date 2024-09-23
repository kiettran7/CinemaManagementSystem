package com.ttk.cinema.DTOs.request.creation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketCreationRequest {
    Float ticketPrice;
    LocalDateTime createdDate;
    String status;
    String bookingType;
    Long showId;    // Liên kết với bảng show_event
    Long seatId;    // Liên kết với bảng seat
    Long customerId; // Liên kết với bảng user
    Long staffId;    // Liên kết với bảng user
    Long movieId;    // Liên kết với bảng movie
}

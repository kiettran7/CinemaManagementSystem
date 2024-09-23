package com.ttk.cinema.DTOs.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketResponse {
    Long ticketId;
    Float ticketPrice;
    LocalDateTime createdDate;
    String status;
    String bookingType;
    ShowEventResponse showId; // Liên kết với show_event
    SeatResponse seatId;      // Liên kết với seat
    UserResponse customerId;  // Liên kết với user
    UserResponse staffId;     // Liên kết với user
    MovieResponse movieId;    // Liên kết với movie
}
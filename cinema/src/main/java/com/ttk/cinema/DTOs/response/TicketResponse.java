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
    ShowEventResponse show; // Liên kết với show_event
    SeatResponse seat;      // Liên kết với seat
    UserResponse customer;  // Liên kết với user
    UserResponse staff;     // Liên kết với user
    MovieResponse movie;    // Liên kết với movie
}
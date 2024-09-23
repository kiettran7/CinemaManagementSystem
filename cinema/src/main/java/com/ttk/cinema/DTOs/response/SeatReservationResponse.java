package com.ttk.cinema.DTOs.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatReservationResponse {
    Long reservationId;
    SeatResponse seatId;
    ShowEventResponse showId;
    String status;
}

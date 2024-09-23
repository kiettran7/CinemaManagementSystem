package com.ttk.cinema.DTOs.request.creation;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillCreationRequest {
    float totalAmount;
    float customerPaid;
    Long promotionId; // Liên kết với bảng promotion
    Long ticketId;    // Liên kết với bảng ticket
}
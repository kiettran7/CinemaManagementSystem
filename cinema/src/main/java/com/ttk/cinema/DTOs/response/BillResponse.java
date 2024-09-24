package com.ttk.cinema.DTOs.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillResponse {
    Long billId;
    Float totalAmount;
    Float customerPaid;
    PromotionResponse promotionId; // Liên kết với promotion
    TicketResponse ticketId;       // Liên kết với ticket
    Set<ItemResponse> items;
}

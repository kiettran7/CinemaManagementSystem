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
    String id;
    Float totalAmount;
    Float customerPaid;
    PromotionResponse promotion; // Liên kết với promotion
    Set<ItemResponse> items;
}

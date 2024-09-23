package com.ttk.cinema.DTOs.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillItemResponse {
    Long billItemId;
    BillResponse billId; // Liên kết với bill
    ItemResponse itemId; // Liên kết với item
    Integer quantity;
}

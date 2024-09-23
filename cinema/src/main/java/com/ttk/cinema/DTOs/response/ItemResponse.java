package com.ttk.cinema.DTOs.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponse {
    Long itemId;
    String itemName;
    Float itemPrice;
}
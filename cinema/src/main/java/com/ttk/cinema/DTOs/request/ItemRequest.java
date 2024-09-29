package com.ttk.cinema.DTOs.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequest {
    String itemName;
    String itemType;
    float itemPrice;
}
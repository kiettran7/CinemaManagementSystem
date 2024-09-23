package com.ttk.cinema.DTOs.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemUpdateRequest {
    String itemName;  // Cập nhật tên mặt hàng
    String itemType;  // Cập nhật loại mặt hàng
    float itemPrice;  // Cập nhật giá mặt hàng
}
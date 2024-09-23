package com.ttk.cinema.DTOs.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionUpdateRequest {
    String promotionName; // Cập nhật tên khuyến mãi
    float discountValue; // Cập nhật giá trị giảm giá
}

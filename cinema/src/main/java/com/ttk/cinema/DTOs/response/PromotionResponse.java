package com.ttk.cinema.DTOs.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionResponse {
    Long promotionId;
    String promotionName;
    Float discountValue;
}

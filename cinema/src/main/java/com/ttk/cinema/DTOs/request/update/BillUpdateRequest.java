package com.ttk.cinema.DTOs.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillUpdateRequest {
    float totalAmount; // Cập nhật tổng số tiền
    float customerPaid; // Cập nhật số tiền khách hàng đã thanh toán
    Long promotionId; // Liên kết với bảng promotion
    Long ticketId;    // Liên kết với bảng ticket
}

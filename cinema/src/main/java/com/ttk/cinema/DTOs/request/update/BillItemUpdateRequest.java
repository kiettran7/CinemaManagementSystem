package com.ttk.cinema.DTOs.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillItemUpdateRequest {
    Long billId;  // Liên kết với bảng bill
    Long itemId;  // Liên kết với bảng item
    long quantity; // Cập nhật số lượng
}
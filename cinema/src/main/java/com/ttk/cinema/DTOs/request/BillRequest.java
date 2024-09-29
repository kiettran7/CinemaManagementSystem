package com.ttk.cinema.DTOs.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillRequest {
//    float totalAmount;
    float customerPaid;
    String promotion; // Liên kết với bảng promotion
    Set<String> tickets;    // Liên kết với bảng ticket
    Set<String> items; // Liên kết với items
}
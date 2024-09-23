package com.ttk.cinema.DTOs.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenreUpdateRequest {
    String genreName; // Cập nhật tên thể loại
}
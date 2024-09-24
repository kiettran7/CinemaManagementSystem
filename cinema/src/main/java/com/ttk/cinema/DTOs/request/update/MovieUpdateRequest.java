package com.ttk.cinema.DTOs.request.update;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieUpdateRequest {
    String movieImage; // Có thể cập nhật hình ảnh
    float moviePrice; // Cập nhật giá
    String movieName; // Cập nhật tên phim
    MultipartFile file;
    int duration; // Cập nhật thời gian
    String status; // Cập nhật trạng thái
    Set<Long> genreIds; // Cập nhật liên kết với bảng genre
    Set<Long> tagIds;   // Cập nhật liên kết với bảng tag
}

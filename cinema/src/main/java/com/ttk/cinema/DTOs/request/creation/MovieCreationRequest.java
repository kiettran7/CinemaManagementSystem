package com.ttk.cinema.DTOs.request.creation;

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
public class MovieCreationRequest {
    String movieName;
    float moviePrice;
    int duration;
    String status;
    Set<Long> genres; // Liên kết với bảng genre
    Set<Long> tags;   // Liên kết với bảng tag
    MultipartFile file;
}
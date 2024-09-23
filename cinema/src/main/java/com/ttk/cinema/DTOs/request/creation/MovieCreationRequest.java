package com.ttk.cinema.DTOs.request.creation;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieCreationRequest {
    String movieImage;
    float moviePrice;
    int duration;
    String status;
    Set<Long> genreIds; // Liên kết với bảng genre
    Set<Long> tagIds;   // Liên kết với bảng tag
}
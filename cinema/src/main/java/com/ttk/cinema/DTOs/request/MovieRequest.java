package com.ttk.cinema.DTOs.request;

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
public class MovieRequest {
    String movieName;
    long moviePrice;
    int duration;
    String status;
    MultipartFile file;
    Set<String> genres;
    Set<String> tags;
}
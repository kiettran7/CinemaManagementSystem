package com.ttk.cinema.DTOs.request.creation;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagCreationRequest {
    String tagName;
}

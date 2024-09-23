package com.ttk.cinema.DTOs.request.update;

import com.ttk.cinema.validators.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String username;
    String password;
    String email;
    String phone;
    String fullName;
    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate birthday;
    String avatar;
    List<String> roles; // Roles like "ROLE_ADMIN", "ROLE_USER"
    MultipartFile file;
}
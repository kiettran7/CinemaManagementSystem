package com.ttk.cinema.POJOs;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long userId;
    String username;
    String password;
    String email;
    String phone;
    String fullName;
    LocalDate birthday;
    LocalDate joinedDate;
    String avatar;
    Set<String> roles;

    @PrePersist
    public void prePersist() {
        if (joinedDate == null) {
            joinedDate = LocalDate.now();
        }
    }
}

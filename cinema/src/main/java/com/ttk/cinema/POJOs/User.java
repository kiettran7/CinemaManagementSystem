package com.ttk.cinema.POJOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

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

    @Transient
    @JsonIgnore
    private MultipartFile file;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_name")
    )
    Set<Role> roles;

    @PrePersist
    public void prePersist() {
        if (joinedDate == null) {
            joinedDate = LocalDate.now();
        }
    }
}

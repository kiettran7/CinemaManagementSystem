package com.ttk.cinema.POJOs;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long promotionId;

    String promotionName;
    float discountValue;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    Set<Bill> bills;
}

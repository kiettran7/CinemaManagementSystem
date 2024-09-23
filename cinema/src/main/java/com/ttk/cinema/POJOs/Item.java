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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long itemId;

    String itemName;
    String itemType; // POPCORN, DRINK
    float itemPrice;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    Set<BillItem> billItems;
}

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
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String itemName;
    String itemType; // POPCORN, DRINK
    long itemPrice;

//    @ManyToMany
//    Set<Bill> bills;

//    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
//    Set<BillItem> billItems;
}

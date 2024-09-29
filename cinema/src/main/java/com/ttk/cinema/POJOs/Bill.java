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
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    float totalAmount;
    float customerPaid;

    @ManyToOne
    Promotion promotion;

    @ManyToMany
    Set<Item> items;

//    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
//    Set<BillItem> billItems;
}

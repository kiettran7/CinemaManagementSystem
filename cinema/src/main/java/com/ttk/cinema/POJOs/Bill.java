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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long billId;

    float totalAmount;
    float customerPaid;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    Ticket ticket;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "bill_item",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    Set<Item> items;

//    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
//    Set<BillItem> billItems;
}

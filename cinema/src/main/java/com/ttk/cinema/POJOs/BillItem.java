//package com.ttk.cinema.POJOs;
//
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@Entity
//public class BillItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    long billItemId;
//
//    @ManyToOne
//    @JoinColumn(name = "bill_id")
//    Bill bill;
//
//    @ManyToOne
//    @JoinColumn(name = "item_id")
//    Item item;
//
//    long quantity;
//}

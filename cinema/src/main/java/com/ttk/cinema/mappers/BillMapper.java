package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.creation.BillCreationRequest;
import com.ttk.cinema.DTOs.request.update.BillUpdateRequest;
import com.ttk.cinema.DTOs.response.BillResponse;
import com.ttk.cinema.POJOs.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

//@Mapper(componentModel = "spring")
//public interface BillMapper {
//    Bill toBill(BillCreationRequest request);
//    BillResponse toBillResponse(Bill bill);
//
//    @Mapping(target = "billItems", ignore = true)
//    void updateBill(@MappingTarget Bill bill, BillUpdateRequest request);
//}
@Mapper(componentModel = "spring")
public interface BillMapper {

    // Mapping từ BillCreationRequest sang Bill (bao gồm Promotion và Ticket)
    @Mapping(target = "items", ignore = true)
    Bill toBill(BillCreationRequest request);

    // Mapping từ Bill sang BillResponse (bao gồm Promotion và Ticket)
    @Mapping(source = "promotion.promotionId", target = "promotionId.promotionId")
    @Mapping(source = "promotion.promotionName", target = "promotionId.promotionName")
    @Mapping(source = "ticket.ticketId", target = "ticketId.ticketId")
    @Mapping(source = "ticket.ticketPrice", target = "ticketId.ticketPrice")
    BillResponse toBillResponse(Bill bill);

    // Mapping khi cập nhật Bill
    @Mapping(source = "promotionId", target = "promotion.promotionId")
    @Mapping(source = "ticketId", target = "ticket.ticketId")
    @Mapping(target = "items", ignore = true) // Bỏ qua billItems vì không thay đổi
    void updateBill(@MappingTarget Bill bill, BillUpdateRequest request);
}


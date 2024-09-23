package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.creation.BillCreationRequest;
import com.ttk.cinema.DTOs.request.update.BillUpdateRequest;
import com.ttk.cinema.DTOs.response.BillResponse;
import com.ttk.cinema.POJOs.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BillMapper {
    Bill toBill(BillCreationRequest request);
    BillResponse toBillResponse(Bill bill);

    @Mapping(target = "items", ignore = true)
    void updateBill(@MappingTarget Bill bill, BillUpdateRequest request);
}

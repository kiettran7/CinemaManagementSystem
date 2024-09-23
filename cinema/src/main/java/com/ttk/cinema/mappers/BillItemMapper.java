package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.creation.BillItemCreationRequest;
import com.ttk.cinema.DTOs.request.update.BillItemUpdateRequest;
import com.ttk.cinema.DTOs.response.BillItemResponse;
import com.ttk.cinema.POJOs.BillItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BillItemMapper {
    BillItem toBillItem(BillItemCreationRequest request);
    BillItemResponse toBillItemResponse(BillItem billItem);

    void updateBillItem(@MappingTarget BillItem billItem, BillItemUpdateRequest request);
}

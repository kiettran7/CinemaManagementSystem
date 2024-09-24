//package com.ttk.cinema.mappers;
//
//import com.ttk.cinema.DTOs.request.creation.BillItemCreationRequest;
//import com.ttk.cinema.DTOs.request.update.BillItemUpdateRequest;
//import com.ttk.cinema.DTOs.response.BillItemResponse;
//import com.ttk.cinema.POJOs.BillItem;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//
//@Mapper(componentModel = "spring")
//public interface BillItemMapper {
//    BillItem toBillItem(BillItemCreationRequest request);
//
//    @Mapping(source = "quantity", target = "quantity")
//    @Mapping(source = "billItemId", target = "billItemId")
//    BillItemResponse toBillItemResponse(BillItem billItem);
//
//    void updateBillItem(@MappingTarget BillItem billItem, BillItemUpdateRequest request);
//}

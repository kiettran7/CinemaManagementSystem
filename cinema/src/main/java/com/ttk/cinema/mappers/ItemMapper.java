package com.ttk.cinema.mappers;

import com.ttk.cinema.DTOs.request.ItemRequest;
import com.ttk.cinema.DTOs.response.ItemResponse;
import com.ttk.cinema.POJOs.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toItem(ItemRequest request);
    ItemResponse toItemResponse(Item item);

    void updateItem(@MappingTarget Item item, ItemRequest request);
}

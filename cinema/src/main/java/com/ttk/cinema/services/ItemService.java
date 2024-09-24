package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.creation.BillCreationRequest;
import com.ttk.cinema.DTOs.request.creation.ItemCreationRequest;
import com.ttk.cinema.DTOs.request.update.BillUpdateRequest;
import com.ttk.cinema.DTOs.request.update.ItemUpdateRequest;
import com.ttk.cinema.DTOs.response.BillResponse;
import com.ttk.cinema.DTOs.response.ItemResponse;
import com.ttk.cinema.POJOs.Bill;
import com.ttk.cinema.POJOs.Item;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.BillMapper;
import com.ttk.cinema.mappers.ItemMapper;
import com.ttk.cinema.repositories.BillRepository;
import com.ttk.cinema.repositories.ItemRepository;
import com.ttk.cinema.repositories.PromotionRepository;
import com.ttk.cinema.repositories.TicketRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemService {
    ItemRepository itemRepository;
    ItemMapper itemMapper;

    public ItemResponse createItem(ItemCreationRequest request) {
        Item item = itemMapper.toItem(request);
        item = itemRepository.save(item);
        return itemMapper.toItemResponse(item);
    }

    public ItemResponse getItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_FOUND));
        return itemMapper.toItemResponse(item);
    }

    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toItemResponse)
                .toList();
    }

    public void deleteItem(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new AppException(ErrorCode.ITEM_NOT_FOUND);
        }
        itemRepository.deleteById(itemId);
    }

    public ItemResponse updateItem(Long itemId, ItemUpdateRequest request) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_FOUND));
        itemMapper.updateItem(item, request);
        item = itemRepository.save(item);
        return itemMapper.toItemResponse(item);
    }
}
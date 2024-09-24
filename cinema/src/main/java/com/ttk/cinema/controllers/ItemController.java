package com.ttk.cinema.controllers;

import com.ttk.cinema.DTOs.request.ApiResponse;
import com.ttk.cinema.DTOs.request.creation.BillCreationRequest;
import com.ttk.cinema.DTOs.request.creation.ItemCreationRequest;
import com.ttk.cinema.DTOs.request.update.BillUpdateRequest;
import com.ttk.cinema.DTOs.request.update.ItemUpdateRequest;
import com.ttk.cinema.DTOs.response.BillResponse;
import com.ttk.cinema.DTOs.response.ItemResponse;
import com.ttk.cinema.services.BillService;
import com.ttk.cinema.services.ItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemController {
    ItemService itemService;

    @PostMapping
    ApiResponse<ItemResponse> createItem(@RequestBody ItemCreationRequest request) {
        return ApiResponse.<ItemResponse>builder()
                .result(itemService.createItem(request))
                .build();
    }

    @GetMapping("/{itemId}")
    ApiResponse<ItemResponse> getItem(@PathVariable Long itemId) {
        return ApiResponse.<ItemResponse>builder()
                .result(itemService.getItem(itemId))
                .build();
    }

    @GetMapping
    ApiResponse<List<ItemResponse>> getAllItems() {
        return ApiResponse.<List<ItemResponse>>builder()
                .result(itemService.getAllItems())
                .build();
    }

    @PutMapping("/{itemId}")
    ApiResponse<ItemResponse> updateItem(@PathVariable Long itemId,
                                         @RequestBody ItemUpdateRequest request) {
        return ApiResponse.<ItemResponse>builder()
                .result(itemService.updateItem(itemId, request))
                .build();
    }

    @DeleteMapping("/{itemId}")
    ApiResponse<Void> deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
        return ApiResponse.<Void>builder().build();
    }
}
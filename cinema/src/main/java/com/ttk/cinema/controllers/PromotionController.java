package com.ttk.cinema.controllers;

import com.ttk.cinema.DTOs.request.ApiResponse;
import com.ttk.cinema.DTOs.request.creation.GenreCreationRequest;
import com.ttk.cinema.DTOs.request.creation.PromotionCreationRequest;
import com.ttk.cinema.DTOs.request.update.GenreUpdateRequest;
import com.ttk.cinema.DTOs.request.update.PromotionUpdateRequest;
import com.ttk.cinema.DTOs.response.GenreResponse;
import com.ttk.cinema.DTOs.response.PromotionResponse;
import com.ttk.cinema.services.GenreService;
import com.ttk.cinema.services.PromotionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PromotionController {
    PromotionService promotionService;

    @PostMapping
    ApiResponse<PromotionResponse> createPromotion(@RequestBody PromotionCreationRequest request) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.createPromotion(request))
                .build();
    }

    @GetMapping("/{promotionId}")
    ApiResponse<PromotionResponse> getPromotion(@PathVariable Long promotionId) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.getPromotion(promotionId))
                .build();
    }

    @GetMapping
    ApiResponse<List<PromotionResponse>> getAllPromotions() {
        return ApiResponse.<List<PromotionResponse>>builder()
                .result(promotionService.getAllPromotions())
                .build();
    }

    @PutMapping("/{promotionId}")
    ApiResponse<PromotionResponse> updatePromotion(@PathVariable Long promotionId,
                                                   @RequestBody PromotionUpdateRequest request) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.updatePromotion(promotionId, request))
                .build();
    }

    @DeleteMapping("/{promotionId}")
    ApiResponse<Void> deletePromotion(@PathVariable Long promotionId) {
        promotionService.deletePromotion(promotionId);
        return ApiResponse.<Void>builder().build();
    }
}
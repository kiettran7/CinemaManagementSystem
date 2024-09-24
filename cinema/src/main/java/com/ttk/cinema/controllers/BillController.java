package com.ttk.cinema.controllers;

import com.ttk.cinema.DTOs.request.ApiResponse;
import com.ttk.cinema.DTOs.request.creation.BillCreationRequest;
import com.ttk.cinema.DTOs.request.creation.GenreCreationRequest;
import com.ttk.cinema.DTOs.request.update.BillUpdateRequest;
import com.ttk.cinema.DTOs.request.update.GenreUpdateRequest;
import com.ttk.cinema.DTOs.response.BillResponse;
import com.ttk.cinema.DTOs.response.GenreResponse;
import com.ttk.cinema.services.BillService;
import com.ttk.cinema.services.GenreService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillController {
    BillService billService;

    @PostMapping
    ApiResponse<BillResponse> createBill(@RequestBody BillCreationRequest request) {
        return ApiResponse.<BillResponse>builder()
                .result(billService.createBill(request))
                .build();
    }

    @GetMapping("/{billId}")
    ApiResponse<BillResponse> getBill(@PathVariable Long billId) {
        return ApiResponse.<BillResponse>builder()
                .result(billService.getBill(billId))
                .build();
    }

    @GetMapping
    ApiResponse<List<BillResponse>> getAllBills() {
        return ApiResponse.<List<BillResponse>>builder()
                .result(billService.getAllBills())
                .build();
    }

    @PutMapping("/{billId}")
    ApiResponse<BillResponse> updateBill(@PathVariable Long billId,
                                         @RequestBody BillUpdateRequest request) {
        return ApiResponse.<BillResponse>builder()
                .result(billService.updateBill(billId, request))
                .build();
    }

    @DeleteMapping("/{billId}")
    ApiResponse<Void> deleteBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
        return ApiResponse.<Void>builder().build();
    }
}
//package com.ttk.cinema.controllers;
//
//import com.ttk.cinema.DTOs.request.ApiResponse;
//import com.ttk.cinema.DTOs.request.creation.BillCreationRequest;
//import com.ttk.cinema.DTOs.request.creation.BillItemCreationRequest;
//import com.ttk.cinema.DTOs.request.update.BillItemUpdateRequest;
//import com.ttk.cinema.DTOs.request.update.BillUpdateRequest;
//import com.ttk.cinema.DTOs.response.BillItemResponse;
//import com.ttk.cinema.DTOs.response.BillResponse;
//import com.ttk.cinema.services.BillItemService;
//import com.ttk.cinema.services.BillService;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/bill-items")
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class BillItemController {
//    BillItemService billItemService;
//
//    @PostMapping
//    ApiResponse<BillItemResponse> createBillItem(@RequestBody BillItemCreationRequest request) {
//        return ApiResponse.<BillItemResponse>builder()
//                .result(billItemService.createBillItem(request))
//                .build();
//    }
//
//    @GetMapping("/{billItemId}")
//    ApiResponse<BillItemResponse> getBillItem(@PathVariable Long billItemId) {
//        return ApiResponse.<BillItemResponse>builder()
//                .result(billItemService.getBillItem(billItemId))
//                .build();
//    }
//
//    @GetMapping
//    ApiResponse<List<BillItemResponse>> getAllBillItems() {
//        return ApiResponse.<List<BillItemResponse>>builder()
//                .result(billItemService.getAllBillItems())
//                .build();
//    }
//
//    @PutMapping("/{billItemId}")
//    ApiResponse<BillItemResponse> updateBillItem(@PathVariable Long billItemId,
//                                                 @RequestBody BillItemUpdateRequest request) {
//        return ApiResponse.<BillItemResponse>builder()
//                .result(billItemService.updateBillItem(billItemId, request))
//                .build();
//    }
//
//    @DeleteMapping("/{billItemId}")
//    ApiResponse<Void> deleteBillItem(@PathVariable Long billItemId) {
//        billItemService.deleteBillItem(billItemId);
//        return ApiResponse.<Void>builder().build();
//    }
//}
//package com.ttk.cinema.services;
//
//import com.ttk.cinema.DTOs.request.creation.BillItemCreationRequest;
//import com.ttk.cinema.DTOs.request.creation.MovieCreationRequest;
//import com.ttk.cinema.DTOs.request.update.BillItemUpdateRequest;
//import com.ttk.cinema.DTOs.request.update.MovieUpdateRequest;
//import com.ttk.cinema.DTOs.response.BillItemResponse;
//import com.ttk.cinema.DTOs.response.MovieResponse;
//import com.ttk.cinema.POJOs.BillItem;
//import com.ttk.cinema.POJOs.Movie;
//import com.ttk.cinema.exceptions.AppException;
//import com.ttk.cinema.exceptions.ErrorCode;
//import com.ttk.cinema.mappers.BillItemMapper;
//import com.ttk.cinema.mappers.MovieMapper;
//import com.ttk.cinema.repositories.BillItemRepository;
//import com.ttk.cinema.repositories.BillRepository;
//import com.ttk.cinema.repositories.ItemRepository;
//import com.ttk.cinema.repositories.MovieRepository;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class BillItemService {
//    BillItemRepository billItemRepository;
//    BillItemMapper billItemMapper;
//    BillRepository billRepository;
//    ItemRepository itemRepository;
//
//    public BillItemResponse createBillItem(BillItemCreationRequest request) {
//        // Kiểm tra các liên kết
//        billRepository.findById(request.getBillId())
//                .orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));
//
//        itemRepository.findById(request.getItemId())
//                .orElseThrow(() -> new AppException(ErrorCode.ITEM_NOT_FOUND));
//
//        BillItem billItem = billItemMapper.toBillItem(request);
//        billItem = billItemRepository.save(billItem);
//        return billItemMapper.toBillItemResponse(billItem);
//    }
//
//    public BillItemResponse getBillItem(Long billItemId) {
//        BillItem billItem = billItemRepository.findById(billItemId)
//                .orElseThrow(() -> new AppException(ErrorCode.BILL_ITEM_NOT_FOUND));
//        return billItemMapper.toBillItemResponse(billItem);
//    }
//
//    public List<BillItemResponse> getAllBillItems() {
//        return billItemRepository.findAll().stream()
//                .map(billItemMapper::toBillItemResponse)
//                .toList();
//    }
//
//    public void deleteBillItem(Long billItemId) {
//        if (!billItemRepository.existsById(billItemId)) {
//            throw new AppException(ErrorCode.BILL_ITEM_NOT_FOUND);
//        }
//        billItemRepository.deleteById(billItemId);
//    }
//
//    public BillItemResponse updateBillItem(Long billItemId, BillItemUpdateRequest request) {
//        BillItem billItem = billItemRepository.findById(billItemId)
//                .orElseThrow(() -> new AppException(ErrorCode.BILL_ITEM_NOT_FOUND));
//        billItemMapper.updateBillItem(billItem, request);
//        billItem = billItemRepository.save(billItem);
//        return billItemMapper.toBillItemResponse(billItem);
//    }
//}
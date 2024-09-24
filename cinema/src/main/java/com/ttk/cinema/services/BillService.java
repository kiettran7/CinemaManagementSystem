package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.creation.BillCreationRequest;
import com.ttk.cinema.DTOs.request.creation.MovieCreationRequest;
import com.ttk.cinema.DTOs.request.update.BillUpdateRequest;
import com.ttk.cinema.DTOs.request.update.MovieUpdateRequest;
import com.ttk.cinema.DTOs.response.BillResponse;
import com.ttk.cinema.DTOs.response.MovieResponse;
import com.ttk.cinema.POJOs.Bill;
import com.ttk.cinema.POJOs.Movie;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.BillMapper;
import com.ttk.cinema.mappers.MovieMapper;
import com.ttk.cinema.repositories.BillRepository;
import com.ttk.cinema.repositories.MovieRepository;
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
public class BillService {
    BillRepository billRepository;
    BillMapper billMapper;
    PromotionRepository promotionRepository;
    TicketRepository ticketRepository;

    public BillResponse createBill(BillCreationRequest request) {
        // Kiểm tra các liên kết
        promotionRepository.findById(request.getPromotionId())
                .orElseThrow(() -> new AppException(ErrorCode.PROMOTION_NOT_FOUND));

        ticketRepository.findById(request.getTicketId())
                .orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_FOUND));

        Bill bill = billMapper.toBill(request);
        bill = billRepository.save(bill);
        return billMapper.toBillResponse(bill);
    }

    public BillResponse getBill(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));
        return billMapper.toBillResponse(bill);
    }

    public List<BillResponse> getAllBills() {
        return billRepository.findAll().stream()
                .map(billMapper::toBillResponse)
                .toList();
    }

    public void deleteBill(Long billId) {
        if (!billRepository.existsById(billId)) {
            throw new AppException(ErrorCode.BILL_NOT_FOUND);
        }
        billRepository.deleteById(billId);
    }

    public BillResponse updateBill(Long billId, BillUpdateRequest request) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));
        billMapper.updateBill(bill, request);
        bill = billRepository.save(bill);
        return billMapper.toBillResponse(bill);
    }
}
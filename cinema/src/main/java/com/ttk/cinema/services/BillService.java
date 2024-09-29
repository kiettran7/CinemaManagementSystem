package com.ttk.cinema.services;

import com.ttk.cinema.DTOs.request.BillRequest;
import com.ttk.cinema.DTOs.response.BillResponse;
import com.ttk.cinema.POJOs.*;
import com.ttk.cinema.exceptions.AppException;
import com.ttk.cinema.exceptions.ErrorCode;
import com.ttk.cinema.mappers.BillMapper;
import com.ttk.cinema.repositories.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BillService {
    BillRepository billRepository;
    BillMapper billMapper;
    PromotionRepository promotionRepository;
    ItemRepository itemRepository;
    TicketRepository ticketRepository;

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'CUSTOMER')")
    @Transactional
    public BillResponse createBill(BillRequest request) {
        Bill bill = billMapper.toBill(request);

        Promotion promotion = promotionRepository.findById(request.getPromotion()).orElseThrow(() -> new AppException(ErrorCode.PROMOTION_NOT_FOUND));
        bill.setPromotion(promotion);
//        Ticket ticket = ticketRepository.findById(request.getTicket()).orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_FOUND));
//        bill.setTicket(ticket);

        billRepository.save(bill);

        float totalAmount = 0;
        for (var ticket : request.getTickets()) {
            Ticket t = ticketRepository.findById(ticket).orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_FOUND));
            System.out.println("t nèeeeeeeee: " + t);
            if (t != null) {
                t.setBill(bill);
                totalAmount += t.getTicketPrice();
                ticketRepository.save(t);
            }
            t = null;
        }
        bill.setTotalAmount(totalAmount);

        var items = itemRepository.findAllById(request.getItems());
        bill.setItems(new HashSet<>(items));

        return billMapper.toBillResponse(billRepository.save(bill));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'CUSTOMER')")
    public BillResponse getBill(String billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));
        return billMapper.toBillResponse(bill);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public List<BillResponse> getAllBills() {
        return billRepository.findAll().stream()
                .map(billMapper::toBillResponse)
                .toList();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public void deleteBill(String billId) {
        if (!billRepository.existsById(billId)) {
            throw new AppException(ErrorCode.BILL_NOT_FOUND);
        }
        billRepository.deleteById(billId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public BillResponse updateBill(String billId, BillRequest request) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_FOUND));
        billMapper.updateBill(bill, request);

        Promotion promotion = promotionRepository.findById(request.getPromotion()).orElseThrow(() -> new AppException(ErrorCode.PROMOTION_NOT_FOUND));
        bill.setPromotion(promotion);
//        Ticket ticket = ticketRepository.findById(request.getTicket()).orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_FOUND));
//        bill.setTicket(ticket);

        var items = itemRepository.findAllById(request.getItems());
        bill.setItems(new HashSet<>(items));

        return billMapper.toBillResponse(billRepository.save(bill));
    }
}
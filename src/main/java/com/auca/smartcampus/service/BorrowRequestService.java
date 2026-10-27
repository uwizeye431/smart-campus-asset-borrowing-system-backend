package com.auca.smartcampus.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auca.smartcampus.domain.BorrowRequest;
import com.auca.smartcampus.domain.BorrowStatus;
import com.auca.smartcampus.repository.BorrowRequestRepository;

@Service
public class BorrowRequestService {

    @Autowired
    private BorrowRequestRepository borrowRequestRepo;

    public String saveBorrowRequest(BorrowRequest borrowRequest) {
        borrowRequest.setStatus(BorrowStatus.REQUESTED);
        borrowRequestRepo.save(borrowRequest);
        return "Borrow request saved successfully";
    }

    public List<BorrowRequest> getAllBorrowRequests() {
        return borrowRequestRepo.findAll();
    }

    public Optional<BorrowRequest> getBorrowRequestById(UUID id) {
        return borrowRequestRepo.findById(id);
    }

    public String updateBorrowRequest(UUID id, BorrowRequest updated) {
        Optional<BorrowRequest> existing = borrowRequestRepo.findById(id);
        if (existing.isEmpty()) {
            return "The borrow request with that id does not exist";
        }
        BorrowRequest borrowRequest = existing.get();
        borrowRequest.setBorrowDate(updated.getBorrowDate());
        borrowRequest.setAssetName(updated.getAssetName());
        borrowRequest.setQuantity(updated.getQuantity());
        borrowRequest.setStatus(updated.getStatus());
        borrowRequest.setStudent(updated.getStudent());
        borrowRequest.setCustodian(updated.getCustodian());
        borrowRequestRepo.save(borrowRequest);
        return "Borrow request updated successfully";
    }

    public String deleteBorrowRequest(UUID id) {
        if (borrowRequestRepo.findById(id).isEmpty()) {
            return "The borrow request with that id does not exist";
        }
        borrowRequestRepo.deleteById(id);
        return "Borrow request deleted successfully";
    }

    public List<BorrowRequest> getBorrowRequestsByStatus(BorrowStatus status) {
        return borrowRequestRepo.findByStatusOrderByBorrowDateAsc(status);
    }

    public List<BorrowRequest> getBorrowRequestsBetween(LocalDate start, LocalDate end) {
        return borrowRequestRepo.findByBorrowDateBetweenOrderByBorrowDateAsc(start, end);
    }
}

package com.auca.smartcampus.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auca.smartcampus.domain.BorrowRequest;
import com.auca.smartcampus.service.BorrowRequestService;

@RestController
@RequestMapping(value = "/api/borrow-requests")
public class BorrowRequestController {

    @Autowired
    private BorrowRequestService borrowRequestService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveBorrowRequest(@RequestBody BorrowRequest borrowRequest)
     {
        String message = borrowRequestService.saveBorrowRequest(borrowRequest);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/all")
    public ResponseEntity<List<BorrowRequest>> getAllBorrowRequests() {
        return new ResponseEntity<>(borrowRequestService.getAllBorrowRequests(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBorrowRequestById(@PathVariable UUID id) 
    {
        return borrowRequestService.getBorrowRequestById(id)
                .<ResponseEntity<?>>map(borrowRequest -> new ResponseEntity<>(borrowRequest, HttpStatus.OK))
                .orElse(new ResponseEntity<>("The borrow request with that id does not exist", HttpStatus.NOT_FOUND));
    }

     @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateBorrowRequest(@PathVariable UUID id, @RequestBody BorrowRequest borrowRequest) {
        String message = borrowRequestService.updateBorrowRequest(id, borrowRequest);
        if (message.equals("Borrow request updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteBorrowRequest(@PathVariable UUID id) 
    {
        String message = borrowRequestService.deleteBorrowRequest(id);
        if (message.equals("Borrow request deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}

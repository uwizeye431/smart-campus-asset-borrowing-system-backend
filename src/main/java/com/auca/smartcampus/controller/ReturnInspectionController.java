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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.auca.smartcampus.domain.ReturnInspection;
import com.auca.smartcampus.service.ReturnInspectionService;

@RestController
@RequestMapping("/api/return-inspections")
public class ReturnInspectionController {

    @Autowired
    private ReturnInspectionService returnInspectionService;

    @PostMapping("/save")
    public ResponseEntity<?> saveReturnInspection(@RequestParam UUID borrowRequestId, @RequestBody ReturnInspection returnInspection) {
        String message = returnInspectionService.saveReturnInspection(borrowRequestId, returnInspection);
        if (message.equals("Return inspection saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        if (message.equals("The borrow request with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/student/{regNumber}")
    public ResponseEntity<List<ReturnInspection>> getByStudentRegNumber(@PathVariable String regNumber) {
        return new ResponseEntity<>(returnInspectionService.getByStudentRegNumber(regNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReturnInspection(@PathVariable UUID id) {
        String message = returnInspectionService.deleteReturnInspection(id);
        if (message.equals("Return inspection deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}

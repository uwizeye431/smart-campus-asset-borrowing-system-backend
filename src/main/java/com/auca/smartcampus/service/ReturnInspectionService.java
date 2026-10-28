package com.auca.smartcampus.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auca.smartcampus.domain.BorrowRequest;
import com.auca.smartcampus.domain.BorrowStatus;
import com.auca.smartcampus.domain.ReturnInspection;
import com.auca.smartcampus.repository.BorrowRequestRepository;
import com.auca.smartcampus.repository.ReturnInspectionRepository;

@Service
public class ReturnInspectionService {

    @Autowired
    private ReturnInspectionRepository returnInspectionRepo;

    @Autowired
    private BorrowRequestRepository borrowRequestRepo;

    public String saveReturnInspection(UUID borrowRequestId, ReturnInspection returnInspection) {
        Optional<BorrowRequest> opt = borrowRequestRepo.findById(borrowRequestId);
        if (opt.isEmpty()) {
            return "The borrow request with that id does not exist";
        }
        BorrowRequest borrowRequest = opt.get();
        if (borrowRequest.getStatus() != BorrowStatus.RETURNED) {
            return "Return inspections are only for RETURNED borrow requests";
        }
        returnInspection.setInspectedDate(LocalDate.now());
        returnInspection.setBorrowRequest(borrowRequest);
        returnInspectionRepo.save(returnInspection);
        return "Return inspection saved successfully";
    }

    public List<ReturnInspection> getByStudentRegNumber(String regNumber) {
        return returnInspectionRepo.findByStudentRegNumber(regNumber);
    }

    public String deleteReturnInspection(UUID id) {
        if (returnInspectionRepo.findById(id).isEmpty()) {
            return "The return inspection with that id does not exist";
        }
        returnInspectionRepo.deleteById(id);
        return "Return inspection deleted successfully";
    }
}

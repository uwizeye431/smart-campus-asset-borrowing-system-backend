package com.auca.smartcampus.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auca.smartcampus.domain.BorrowStatus;
import com.auca.smartcampus.repository.BorrowRequestRepository;
import com.auca.smartcampus.repository.CustodianRepository;
import com.auca.smartcampus.repository.ReturnInspectionRepository;
import com.auca.smartcampus.repository.StoreRepository;
import com.auca.smartcampus.repository.StudentRepository;

@Service
public class DashboardService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CustodianRepository custodianRepo;

    @Autowired
    private StoreRepository storeRepo;

    @Autowired
    private BorrowRequestRepository borrowRequestRepo;

    @Autowired
    private ReturnInspectionRepository returnInspectionRepo;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("students", studentRepo.count());
        summary.put("custodians", custodianRepo.count());
        summary.put("freeStores", (long) storeRepo.findFreeStores().size());
        summary.put("requestsToday", borrowRequestRepo.countByBorrowDate(LocalDate.now()));
        summary.put("requested", borrowRequestRepo.countByStatus(BorrowStatus.REQUESTED));
        summary.put("returnInspections", returnInspectionRepo.count());
        return summary;
    }
}

package com.auca.smartcampus.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.smartcampus.domain.BorrowRequest;
import com.auca.smartcampus.domain.BorrowStatus;

@Repository
public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, UUID> {

    List<BorrowRequest> findByStatusOrderByBorrowDateAsc(BorrowStatus status);

    List<BorrowRequest> findByBorrowDateBetweenOrderByBorrowDateAsc(LocalDate start, LocalDate end);

    boolean existsByCustodianIdAndBorrowDateAndStatusNot(UUID custodianId, LocalDate borrowDate, BorrowStatus status);
}

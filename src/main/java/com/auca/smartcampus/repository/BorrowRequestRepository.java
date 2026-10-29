package com.auca.smartcampus.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.auca.smartcampus.domain.BorrowRequest;
import com.auca.smartcampus.domain.BorrowStatus;

@Repository
public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, UUID> {

    List<BorrowRequest> findByStatusOrderByBorrowDateAsc(BorrowStatus status);

    List<BorrowRequest> findByBorrowDateBetweenOrderByBorrowDateAsc(LocalDate start, LocalDate end);

    boolean existsByCustodianIdAndBorrowDateAndStatusNot(UUID custodianId, LocalDate borrowDate, BorrowStatus status);

    @Query("SELECT b.status, COUNT(b) FROM BorrowRequest b GROUP BY b.status")
    List<Object[]> countBorrowRequestsByStatus();

    @Modifying
    @Query("UPDATE BorrowRequest b SET b.status = :rejected WHERE b.custodian.id = :custodianId AND b.borrowDate = :date AND b.status <> :returned")
    int cancelBorrowRequestsOfDay(@Param("custodianId") UUID custodianId, @Param("date") LocalDate date, @Param("rejected") BorrowStatus rejected, @Param("returned") BorrowStatus returned);
}

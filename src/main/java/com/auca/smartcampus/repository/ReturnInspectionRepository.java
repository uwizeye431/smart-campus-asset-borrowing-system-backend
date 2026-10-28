package com.auca.smartcampus.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.auca.smartcampus.domain.BorrowRequest;
import com.auca.smartcampus.domain.ReturnInspection;

@Repository
public interface ReturnInspectionRepository extends JpaRepository<ReturnInspection, UUID> {

    boolean existsByBorrowRequest(BorrowRequest borrowRequest);

    @Query("SELECT d FROM ReturnInspection d WHERE d.borrowRequest.student.regNumber = :regNumber ORDER BY d.inspectedDate DESC")
    List<ReturnInspection> findByStudentRegNumber(@Param("regNumber") String regNumber);
}

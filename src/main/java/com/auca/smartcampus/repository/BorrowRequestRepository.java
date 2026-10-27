package com.auca.smartcampus.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.smartcampus.domain.BorrowRequest;

@Repository
public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, UUID> {
}

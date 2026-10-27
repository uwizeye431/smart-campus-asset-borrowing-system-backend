package com.auca.smartcampus.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auca.smartcampus.domain.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID>
 {
}

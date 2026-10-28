package com.auca.smartcampus.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auca.smartcampus.domain.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID>
 {
    Optional<Store> findByStoreNumber(int storeNumber);

    @Query("SELECT s FROM Store s WHERE NOT EXISTS (SELECT c FROM Custodian c WHERE c.store = s) ORDER BY s.storeNumber ASC")
    List<Store> findFreeStores();
}

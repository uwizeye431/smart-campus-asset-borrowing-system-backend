package com.auca.smartcampus.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.auca.smartcampus.domain.Custodian;
import com.auca.smartcampus.domain.Store;

@Repository
public interface CustodianRepository extends JpaRepository<Custodian, UUID>
{
    boolean existsByStore(Store store);

    @Query("SELECT c FROM Custodian c JOIN c.assetTypes a WHERE LOWER(a.name) = LOWER(:name)")
    List<Custodian> findCustodiansByAssetTypeName(@Param("name") String name);
}

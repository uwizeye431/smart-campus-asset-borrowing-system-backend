package com.auca.smartcampus.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auca.smartcampus.domain.AssetType;

@Repository
public interface AssetTypeRepository extends JpaRepository<AssetType, UUID> 
{
    @Query("SELECT a FROM AssetType a WHERE a.custodians IS EMPTY")
    List<AssetType> findUnusedAssetTypes();
}

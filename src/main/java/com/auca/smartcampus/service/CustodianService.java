package com.auca.smartcampus.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.auca.smartcampus.domain.AssetType;
import com.auca.smartcampus.domain.Custodian;
import com.auca.smartcampus.domain.Store;
import com.auca.smartcampus.repository.AssetTypeRepository;
import com.auca.smartcampus.repository.CustodianRepository;
import com.auca.smartcampus.repository.StoreRepository;

@Service
public class CustodianService {

    @Autowired
    private CustodianRepository custodianRepo;

    @Autowired
    private AssetTypeRepository assetTypeRepo;

    @Autowired
    private StoreRepository storeRepo;

    public String saveCustodian(Custodian custodian) {
        custodianRepo.save(custodian);
        return "Custodian saved successfully";
    }

    public List<Custodian> getAllCustodians() {
        return custodianRepo.findAll();
    }

    public Optional<Custodian> getCustodianById(UUID id) {
        return custodianRepo.findById(id);
    }

    public String updateCustodian(UUID id, Custodian updated) {
        Optional<Custodian> existing = custodianRepo.findById(id);
        if (existing.isEmpty()) {
            return "The custodian with that id does not exist";
        }
        Custodian custodian = existing.get();
        custodian.setFirstName(updated.getFirstName());
        custodian.setLastName(updated.getLastName());
        custodian.setEmail(updated.getEmail());
        custodian.setStore(updated.getStore());
        custodianRepo.save(custodian);
        return "Custodian updated successfully";
    }

    public String deleteCustodian(UUID id) {
        if (custodianRepo.findById(id).isEmpty()) {
            return "The custodian with that id does not exist";
        }
        custodianRepo.deleteById(id);
        return "Custodian deleted successfully";
    }

    public List<Custodian> getCustodiansByAssetType(String name) {
        return custodianRepo.findCustodiansByAssetTypeName(name);
    }

    @Transactional
    public String addAssetType(UUID custodianId, UUID assetTypeId) {
        Optional<Custodian> custodianOpt = custodianRepo.findById(custodianId);
        if (custodianOpt.isEmpty()) {
            return "The custodian with that id does not exist";
        }
        Optional<AssetType> assetTypeOpt = assetTypeRepo.findById(assetTypeId);
        if (assetTypeOpt.isEmpty()) {
            return "That asset type does not exist";
        }
        Custodian custodian = custodianOpt.get();
        AssetType assetType = assetTypeOpt.get();
        if (custodian.getAssetTypes().contains(assetType)) {
            return "The custodian already has that asset type";
        }
        custodian.getAssetTypes().add(assetType);
        custodianRepo.save(custodian);
        return "Asset type added to the custodian";
    }

    public String changeStore(UUID custodianId, int storeNumber) {
        Optional<Custodian> custodianOpt = custodianRepo.findById(custodianId);
        if (custodianOpt.isEmpty()) {
            return "The custodian with that id does not exist";
        }
        Optional<Store> storeOpt = storeRepo.findByStoreNumber(storeNumber);
        if (storeOpt.isEmpty()) {
            return "The store with that storeNumber does not exist";
        }
        Custodian custodian = custodianOpt.get();
        Store store = storeOpt.get();
        if (custodianRepo.existsByStore(store) && (custodian.getStore() == null || !custodian.getStore().getId().equals(store.getId()))) {
            return "That store is taken by another custodian";
        }
        custodian.setStore(store);
        custodianRepo.save(custodian);
        return "Store changed successfully";
    }
}

package com.auca.smartcampus.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.smartcampus.domain.AssetType;
import com.auca.smartcampus.repository.AssetTypeRepository;

@Service
public class AssetTypeService {

    @Autowired
    private AssetTypeRepository assetTypeRepo;

    public String saveAssetType(AssetType assetType)
     {
        assetTypeRepo.save(assetType);
        return "Asset type saved successfully";
    }
    public List<AssetType> getAllAssetTypes() {
        return assetTypeRepo.findAll();
    }
    public Optional<AssetType> getAssetTypeById(UUID id) {
        return assetTypeRepo.findById(id);
    }
    public String updateAssetType(UUID id, AssetType updated) {
        Optional<AssetType> existing = assetTypeRepo.findById(id);
        if (existing.isEmpty()) {
            return "The asset type with that id does not exist";
        }
        AssetType assetType = existing.get();
        assetType.setName(updated.getName());
        assetTypeRepo.save(assetType);
        return "Asset type updated successfully";
    }
    public String deleteAssetType(UUID id) {
        if (assetTypeRepo.findById(id).isEmpty()) {
            return "The asset type with that id does not exist";
        }
        assetTypeRepo.deleteById(id);
        return "Asset type deleted successfully";
    }
}

package com.auca.smartcampus.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auca.smartcampus.domain.Store;
import com.auca.smartcampus.repository.StoreRepository;

@Service
public class StoreService
 {
    @Autowired
    private StoreRepository storeRepo;

    public String saveStore(Store store) {
        storeRepo.save(store);
        return "Store saved successfully";
    }
    public List<Store> getAllStores() {
        return storeRepo.findAll();
    }
    public Optional<Store> getStoreById(UUID id) {
        return storeRepo.findById(id);
    }
    public String updateStore(UUID id, Store updated) {
        Optional<Store> existing = storeRepo.findById(id);
        if (existing.isEmpty()) {
            return "The store with that id does not exist";
        }
        Store store = existing.get();
        store.setStoreNumber(updated.getStoreNumber());
        store.setName(updated.getName());
        storeRepo.save(store);
        return "Store updated successfully";
    }
    public String deleteStore(UUID id) {
        if (storeRepo.findById(id).isEmpty()) {
            return "The store with that id does not exist";
        }
        storeRepo.deleteById(id);
        return "Store deleted successfully";
    }
}

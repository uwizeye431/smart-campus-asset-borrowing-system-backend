package com.auca.smartcampus.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auca.smartcampus.domain.Custodian;
import com.auca.smartcampus.repository.CustodianRepository;

@Service
public class CustodianService
 {
    @Autowired
    private CustodianRepository custodianRepo;

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
}

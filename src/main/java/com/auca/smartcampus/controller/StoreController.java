package com.auca.smartcampus.controller;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auca.smartcampus.domain.Store;
import com.auca.smartcampus.service.StoreService;

@RestController
@RequestMapping(value = "/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveStore(@RequestBody Store store)
     {
        String message = storeService.saveStore(store);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Store>> getAllStores() {
        return new ResponseEntity<>(storeService.getAllStores(), HttpStatus.OK);
    }

    @GetMapping(value = "/free")
    public ResponseEntity<List<Store>> getFreeStores() {
        return new ResponseEntity<>(storeService.getFreeStores(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getStoreById(@PathVariable UUID id) {
        return storeService.getStoreById(id)
                .<ResponseEntity<?>>map(store -> new ResponseEntity<>(store, HttpStatus.OK))
                .orElse(new ResponseEntity<>("The store with that id does not exist", HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateStore(@PathVariable UUID id, @RequestBody Store store) {
        String message = storeService.updateStore(id, store);
        if (message.equals("Store updated successfully"))
             {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable UUID id) {
        String message = storeService.deleteStore(id);
        if (message.equals("Store deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}

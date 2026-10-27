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
import com.auca.smartcampus.domain.Custodian;
import com.auca.smartcampus.service.CustodianService;

@RestController
@RequestMapping(value = "/api/custodians")
public class CustodianController
 {
    @Autowired
    private CustodianService custodianService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveCustodian(@RequestBody Custodian custodian) 
    {
        String message = custodianService.saveCustodian(custodian);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Custodian>> getAllCustodians() {
        return new ResponseEntity<>(custodianService.getAllCustodians(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCustodianById(@PathVariable UUID id) {
        return custodianService.getCustodianById(id)
                .<ResponseEntity<?>>map(custodian -> new ResponseEntity<>(custodian, HttpStatus.OK))
                .orElse(new ResponseEntity<>("The custodian with that id does not exist", HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCustodian(@PathVariable UUID id, @RequestBody Custodian custodian) {
        String message = custodianService.updateCustodian(id, custodian);
        if (message.equals("Custodian updated successfully")) 
            {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCustodian(@PathVariable UUID id) 
    {
        String message = custodianService.deleteCustodian(id);
        if (message.equals("Custodian deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}

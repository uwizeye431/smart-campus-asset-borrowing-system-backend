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
import com.auca.smartcampus.domain.AssetType;
import com.auca.smartcampus.service.AssetTypeService;

@RestController
@RequestMapping(value = "/api/asset-types")
public class AssetTypeController {
    @Autowired
    private AssetTypeService assetTypeService;
 
    @PostMapping(value = "/save")
    public ResponseEntity<?> saveAssetType(@RequestBody AssetType assetType) 
    {
        String message = assetTypeService.saveAssetType(assetType);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/all")
    public ResponseEntity<List<AssetType>> getAllAssetTypes()
     {
        return new ResponseEntity<>(assetTypeService.getAllAssetTypes(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAssetTypeById(@PathVariable UUID id) 
    {
        return assetTypeService.getAssetTypeById(id)
                .<ResponseEntity<?>>map(assetType -> new ResponseEntity<>(assetType, HttpStatus.OK))
                .orElse(new ResponseEntity<>("The asset type with that id does not exist", HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateAssetType(@PathVariable UUID id, @RequestBody AssetType assetType) {
        String message = assetTypeService.updateAssetType(id, assetType);
        if (message.equals("Asset type updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteAssetType(@PathVariable UUID id)
     {
        String message = assetTypeService.deleteAssetType(id);
        if (message.equals("Asset type deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}

package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.dto.impl.CropDTO;
import lk.ijse.greenshadowbackendapi.dto.impl.FieldDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin
public class CropController {
    @PostMapping
    public ResponseEntity<CropDTO> saveCrop(@RequestBody FieldDTO fieldDTO) {
        return null;
    }

    // Get all fields
    @GetMapping
    public List<CropDTO> getAllCrops() {
        return null;
    }

    // Get a field by ID
    @GetMapping("/{id}")
    public ResponseEntity<CropDTO> getFieldById(@PathVariable Long id) {
        return null;
    }

    // Update a field
    @PutMapping
    public ResponseEntity<CropDTO> updateField(@PathVariable Long id, @RequestBody FieldDTO fieldDTO) {
        return null;
    }

    // Delete a field
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        return null;
    }
}

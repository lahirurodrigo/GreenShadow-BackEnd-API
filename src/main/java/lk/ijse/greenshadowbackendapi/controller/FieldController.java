package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.dto.impl.FieldDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/fields")
@CrossOrigin
public class FieldController {
    @PostMapping
    public ResponseEntity<FieldDTO> createField(@RequestBody FieldDTO fieldDTO) {
        return null;
    }

    // Get all fields
    @GetMapping
    public List<FieldDTO> getAllFields() {
        return null;
    }

    // Get a field by ID
    @GetMapping("/{id}")
    public ResponseEntity<FieldDTO> getFieldById(@PathVariable Long id) {
        return null;
    }

    // Update a field
    @PutMapping
    public ResponseEntity<FieldDTO> updateField(@PathVariable Long id, @RequestBody FieldDTO fieldDTO) {
        return null;
    }

    // Delete a field
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        return null;
    }
}

package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.dto.impl.FieldStaffDTO;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.FieldNotFoundException;
import lk.ijse.greenshadowbackendapi.exception.StaffNotFoundException;
import lk.ijse.greenshadowbackendapi.service.impl.FieldStaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/field-staff")
@CrossOrigin
public class FieldStaffController {

    @Autowired
    private FieldStaffServiceImpl fieldStaffService;

    // Add staff members to a field
    @PostMapping({"/{fieldId}"})
    public ResponseEntity<Void> addStaffToField(
            @PathVariable String fieldId,
            @RequestBody List<String> staffIds) {
        try {
            fieldStaffService.addStaffToField(fieldId, staffIds);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (FieldNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Remove a staff member from a field
    @DeleteMapping("/{fieldId}/staff/{staffId}")
    public ResponseEntity<Void> removeStaffFromField(
            @PathVariable String fieldId,
            @PathVariable String staffId) {
        try {
            fieldStaffService.removeStaffFromField(fieldId, staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  Get all staff members associated with a field
    @GetMapping("/{fieldId}")
    public ResponseEntity<List<FieldStaffDTO>> getStaffForField(
            @PathVariable String fieldId) {
        try {
            List<String> staffIds = fieldStaffService.getStaffForField(fieldId);
            if (staffIds.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<FieldStaffDTO> staffDTOs = staffIds.stream()
                    .map(staffId -> new FieldStaffDTO(fieldId,staffId))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(staffDTOs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

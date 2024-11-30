package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.dto.StaffStatus;
import lk.ijse.greenshadowbackendapi.dto.impl.StaffDTO;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.StaffNotFoundException;
import lk.ijse.greenshadowbackendapi.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/staff")
@CrossOrigin
public class StaffController {

    @Autowired
    private StaffService staffService;

    // Save staff details
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDTO> saveStaff(@RequestBody StaffDTO staffDTO) {
        try {
            // Save staff details
            System.out.println("Received DOB: " + staffDTO.getDOB());
            staffService.saveStaff(staffDTO);
            return new ResponseEntity<>(staffDTO, HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all staff
    @GetMapping
    public List<StaffDTO> getAllStaff() {
        return staffService.getAllStaffs();
    }

    // Get a staff member by ID
    @GetMapping("/{id}")
    public StaffStatus getStaffById(@PathVariable String id) {
        return staffService.getStaff(id);
    }

    // Update staff details
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStaff(
            @PathVariable String id,
            @RequestBody StaffDTO staffDTO
    ) {
        try {
            // Update staff details
            staffService.updateStaff(id, staffDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a staff member
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable String id) {
        try {
            staffService.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

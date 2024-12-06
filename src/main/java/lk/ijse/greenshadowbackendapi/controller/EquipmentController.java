package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.dto.impl.EquipmentDTO;
import lk.ijse.greenshadowbackendapi.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackendapi.dto.impl.StaffDTO;
import lk.ijse.greenshadowbackendapi.entity.FieldEntity;
import lk.ijse.greenshadowbackendapi.entity.StaffEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.EquipmentNotFoundException;
import lk.ijse.greenshadowbackendapi.service.EquipmentService;
import lk.ijse.greenshadowbackendapi.service.FieldService;
import lk.ijse.greenshadowbackendapi.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
@CrossOrigin
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private FieldService fieldService;

    // Save equipment details
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentDTO> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        try {
//            // Create the EquipmentDTO from the form parameters
//            EquipmentDTO equipmentDTO = new EquipmentDTO();
//            equipmentDTO.setEquipmentId(equipmentId);
//            equipmentDTO.setEquipmentName(equipmentName);
//            equipmentDTO.setEquipmentType(equipmentType);
//            equipmentDTO.setStatus(status);
//
//            // Get the staff and field details
//            StaffDTO staff = (StaffDTO) staffService.getStaff(staffId);
//            FieldDTO field = (FieldDTO) fieldService.getField(fieldCode);
//
//            equipmentDTO.setStaff(staff);
//            equipmentDTO.setFields(field);

            // Save equipment details using the service
            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>(equipmentDTO, HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all equipment
    @GetMapping
    public List<EquipmentDTO> getAllEquipment() {
        return equipmentService.getAllEquipments();
    }

    // Get equipment by ID
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable String id) {
        try {
            EquipmentDTO equipmentDTO = equipmentService.getEquipment(id);
            return new ResponseEntity<>(equipmentDTO, HttpStatus.OK);
        } catch (EquipmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update equipment details
    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDTO> updateEquipment(
            @PathVariable String id,
            @RequestBody EquipmentDTO equipmentDTO
    ) {
        try {
//            // Create the EquipmentDTO from the form parameters
//            EquipmentDTO equipmentDTO = new EquipmentDTO();
//            equipmentDTO.setEquipmentId(id);
//            equipmentDTO.setEquipmentName(equipmentName);
//            equipmentDTO.setEquipmentType(equipmentType);
//            equipmentDTO.setStatus(status);

//            // Fetch the related Staff and Field entities using the provided staffId and fieldCode
//            StaffDTO staff = (StaffDTO) staffService.getStaff(staffId);
//            FieldDTO field = (FieldDTO) fieldService.getField(fieldCode);
//
//            // Set the Staff and Field DTOs to the EquipmentDTO
//            equipmentDTO.setStaff(staff);
//            equipmentDTO.setFields(field);

            // Update equipment details using the service
            equipmentService.updateEquipment(id, equipmentDTO);

            // Return the updated EquipmentDTO with HTTP status NO CONTENT (204)
            return new ResponseEntity<>(equipmentDTO, HttpStatus.NO_CONTENT);
        } catch (EquipmentNotFoundException e) {
            e.printStackTrace();
            // Return NOT FOUND (404) if the equipment ID is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            // Return INTERNAL SERVER ERROR (500) for unexpected errors
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete equipment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable String id) {
        try {
            equipmentService.deleteEquipment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

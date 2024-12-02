package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.dto.impl.VehicleDTO;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle")
@CrossOrigin
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Save vehicle details
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            // Save vehicle details
            System.out.println("Received vehicleCode: " + vehicleDTO.getVehicleCode());
            vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity<>(vehicleDTO, HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all vehicles
    @GetMapping
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
}

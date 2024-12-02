package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/vehicle")
@CrossOrigin
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
}

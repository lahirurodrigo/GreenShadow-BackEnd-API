package lk.ijse.greenshadowbackendapi.service;

import lk.ijse.greenshadowbackendapi.dto.VehicleStatus;
import lk.ijse.greenshadowbackendapi.dto.impl.VehicleDTO;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO) throws DataPersistException;

    List<VehicleDTO> getAllVehicles();

    VehicleStatus getVehicle(String vehicleCode);

    void deleteVehicle(String vehicleCode);

    void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO);
}

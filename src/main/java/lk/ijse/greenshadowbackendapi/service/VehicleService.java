package lk.ijse.greenshadowbackendapi.service;

import lk.ijse.greenshadowbackendapi.dto.impl.VehicleDTO;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO) throws DataPersistException;
}

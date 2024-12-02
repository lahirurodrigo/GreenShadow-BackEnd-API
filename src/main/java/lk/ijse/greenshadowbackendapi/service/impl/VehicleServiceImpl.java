package lk.ijse.greenshadowbackendapi.service.impl;

import lk.ijse.greenshadowbackendapi.dao.VehicleDAO;
import lk.ijse.greenshadowbackendapi.dto.impl.VehicleDTO;
import lk.ijse.greenshadowbackendapi.entity.VehicleEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.service.VehicleService;
import lk.ijse.greenshadowbackendapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleDAO vehicleDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) throws DataPersistException {
        // Check if the vehicleCode already exists
        if (vehicleDAO.existsById(vehicleDTO.getVehicleCode())) {
            throw new DataPersistException("Vehicle with the same code already exists!");
        }

        // Convert DTO to Entity
        VehicleEntity vehicleEntity = mapping.toVehicleEntity(vehicleDTO);

        // Save the vehicle entity
        VehicleEntity savedVehicle = vehicleDAO.save(vehicleEntity);
        if (savedVehicle == null) {
            throw new DataPersistException("Vehicle not saved!");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<VehicleEntity> allVehicles = vehicleDAO.findAll();
        return mapping.toVehicleDTOList(allVehicles);
    }

}

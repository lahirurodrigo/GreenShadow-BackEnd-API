package lk.ijse.greenshadowbackendapi.service.impl;

import lk.ijse.greenshadowbackendapi.customStatusCode.SelectedEntityErrorStatus;
import lk.ijse.greenshadowbackendapi.dao.StaffDAO;
import lk.ijse.greenshadowbackendapi.dao.VehicleDAO;
import lk.ijse.greenshadowbackendapi.dto.VehicleStatus;
import lk.ijse.greenshadowbackendapi.dto.impl.VehicleDTO;
import lk.ijse.greenshadowbackendapi.entity.VehicleEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.VehicleNotFoundException;
import lk.ijse.greenshadowbackendapi.service.VehicleService;
import lk.ijse.greenshadowbackendapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleDAO vehicleDAO;

    @Autowired
    private StaffDAO staffDAO;

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

    @Override
    public VehicleStatus getVehicle(String vehicleCode) {
        if (vehicleDAO.existsById(vehicleCode)) {
            VehicleEntity selectedVehicle = vehicleDAO.getReferenceById(vehicleCode);
            return mapping.toVehicleDTO(selectedVehicle);
        } else {
            return new SelectedEntityErrorStatus(2, "Vehicle with code " + vehicleCode + " not found!");
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        if (vehicleDAO.existsById(vehicleCode)) {
            vehicleDAO.deleteById(vehicleCode);
        } else {
            throw new VehicleNotFoundException("Vehicle with code " + vehicleCode + " not Found");
        }
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {
        if (!vehicleDAO.existsById(vehicleCode)) {
            throw new VehicleNotFoundException("Vehicle with code " + vehicleCode + " not found");
        }

        // Find the existing vehicle entity by vehicleCode
        Optional<VehicleEntity> existedVehicle = vehicleDAO.findById(vehicleCode);

        if (existedVehicle.isPresent()) {
            VehicleEntity vehicleEntity = existedVehicle.get();

            // Set the updated values from VehicleDTO to the existing VehicleEntity
            vehicleEntity.setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
            vehicleEntity.setVehicleCategory(vehicleDTO.getVehicleCategory());
            vehicleEntity.setFuelType(vehicleDTO.getFuelType());
            vehicleEntity.setStatus(vehicleDTO.getStatus());
            vehicleEntity.setRemarks(vehicleDTO.getRemarks());
            vehicleEntity.setStaff(mapping.toStaffEntity(vehicleDTO.getStaff()));
            vehicleEntity.setAssistantId(vehicleDTO.getAssistantId());
        } else {
            throw new VehicleNotFoundException("Vehicle with code " + vehicleCode + " not found");
        }
    }

}

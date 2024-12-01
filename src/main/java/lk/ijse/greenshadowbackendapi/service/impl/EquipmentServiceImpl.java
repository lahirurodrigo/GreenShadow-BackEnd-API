package lk.ijse.greenshadowbackendapi.service.impl;

import lk.ijse.greenshadowbackendapi.dao.EquipmentDAO;
import lk.ijse.greenshadowbackendapi.dto.impl.EquipmentDTO;
import lk.ijse.greenshadowbackendapi.entity.EquipmentEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.EquipmentNotFoundException;
import lk.ijse.greenshadowbackendapi.service.EquipmentService;
import lk.ijse.greenshadowbackendapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentDAO equipmentDAO; // EquipmentDAO extends JpaRepository
    @Autowired
    private Mapping mapping; // For DTO <-> Entity conversion

    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) throws DataPersistException {
        // Check if the equipmentId already exists
        if (equipmentDAO.existsById(equipmentDTO.getEquipmentId())) {
            throw new DataPersistException("Equipment with the same ID already exists!");
        }

        // Convert DTO to Entity and save
        EquipmentEntity equipmentEntity = mapping.toEquipmentEntity(equipmentDTO);
        equipmentDAO.save(equipmentEntity);
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        List<EquipmentEntity> allEquipments = equipmentDAO.findAll();
        return mapping.toEquipmentDTOList(allEquipments);
    }

    @Override
    public EquipmentDTO getEquipment(String equipmentId) {
        EquipmentEntity equipmentEntity = equipmentDAO.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment with ID " + equipmentId + " not found!"));
        return mapping.toEquipmentDTO(equipmentEntity);
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        if (equipmentDAO.existsById(equipmentId)) {
            equipmentDAO.deleteById(equipmentId);
        } else {
            throw new EquipmentNotFoundException("Equipment with ID " + equipmentId + " not found!");
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        EquipmentEntity equipmentEntity = equipmentDAO.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment with ID " + equipmentId + " not found!"));

        // Update the fields
        equipmentEntity.setEquipmentName(equipmentDTO.getEquipmentName());
        equipmentEntity.setEquipmentType(equipmentDTO.getEquipmentType());
        equipmentEntity.setStatus(equipmentDTO.getStatus());
        equipmentEntity.setStaff(mapping.toStaffEntity(equipmentDTO.getStaff()));
        equipmentEntity.setFields(mapping.toFieldEntity(equipmentDTO.getFields()));

        // Save the updated entity
        equipmentDAO.save(equipmentEntity);
    }
}

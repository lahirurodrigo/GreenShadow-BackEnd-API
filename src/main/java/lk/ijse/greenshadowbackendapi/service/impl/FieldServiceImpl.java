package lk.ijse.greenshadowbackendapi.service.impl;

import lk.ijse.greenshadowbackendapi.customStatusCode.SelectedEntityErrorStatus;
import lk.ijse.greenshadowbackendapi.dao.FieldDAO;
import lk.ijse.greenshadowbackendapi.dto.FieldStatus;
import lk.ijse.greenshadowbackendapi.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackendapi.entity.FieldEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.FieldNotFoundException;
import lk.ijse.greenshadowbackendapi.exception.StaffNotFoundException;
import lk.ijse.greenshadowbackendapi.service.FieldService;
import lk.ijse.greenshadowbackendapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDAO fieldDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveField(FieldDTO fieldDTO) throws DataPersistException {
        // Check if the staffId already exists
        if (fieldDao.existsById(fieldDTO.getFieldCode())) {
            throw new DataPersistException("Field with the same ID already exists!");
        }

        // Convert DTO to Entity and Save
        FieldEntity savedField = fieldDao.save(mapping.toFieldEntity(fieldDTO));
        if (savedField == null) {
            throw new DataPersistException("Field not saved!");
        }
    }


    // Get all fields
    @Override
    public List<FieldDTO> getAllFields() {
        // Get all FieldEntities and convert to DTOs and return
        List<FieldEntity> allFields = fieldDao.findAll();
        return mapping.toFieldDTOList(allFields);
    }

    // Get a specific field
    @Override
    public FieldStatus getField(String fieldCode) {
        if (fieldDao.existsById(fieldCode)) {
            FieldEntity selectedField = fieldDao.getReferenceById(fieldCode);
            return mapping.toFieldDTO(selectedField);
        } else {
            return new SelectedEntityErrorStatus(2, "Field with id " + fieldCode + "not found!");
        }
    }

    // Delete a field
    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> existedField = fieldDao.findById(fieldCode);
        if (!existedField.isPresent()) {
            throw new FieldNotFoundException("Field with id " + fieldCode + " not Found");
        } else {
            fieldDao.deleteById(fieldCode);
        }
    }

    // Update a field
    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        if (!fieldDao.existsById(fieldDTO.getFieldCode())) {
            throw new StaffNotFoundException("Field with Code " + fieldCode + " not found");
        }

        Optional<FieldEntity> tempField = fieldDao.findById(fieldCode);
        if (tempField.isPresent()) {
            tempField.get().setFieldName(fieldDTO.getFieldName());
            tempField.get().setFieldLocation(fieldDTO.getFieldLocation());
            tempField.get().setFieldSize(fieldDTO.getFieldSize());
            tempField.get().setFieldImage01(fieldDTO.getFieldImage01());
            tempField.get().setFieldImage02(fieldDTO.getFieldImage02());
            tempField.get().setCrop(mapping.toCropEntity(fieldDTO.getCrop()));
        }else {
            throw new FieldNotFoundException("Field with id " + fieldCode + " not Found");
        }
    }
}

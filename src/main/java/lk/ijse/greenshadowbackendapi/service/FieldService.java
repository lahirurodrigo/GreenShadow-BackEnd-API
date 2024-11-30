package lk.ijse.greenshadowbackendapi.service;


import lk.ijse.greenshadowbackendapi.dto.FieldStatus;
import lk.ijse.greenshadowbackendapi.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO) throws DataPersistException ;
    List<FieldDTO> getAllFields();
    FieldStatus getField(String fieldCode);
    void deleteField(String fieldCode);
    void updateField(String fieldCode,FieldDTO fieldDTO);
}

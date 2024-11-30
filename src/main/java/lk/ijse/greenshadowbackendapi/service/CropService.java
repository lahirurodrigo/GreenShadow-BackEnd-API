package lk.ijse.greenshadowbackendapi.service;

import lk.ijse.greenshadowbackendapi.dto.CropStatus;
import lk.ijse.greenshadowbackendapi.dto.impl.CropDTO;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO) throws DataPersistException;

    List<CropDTO> getAllCrops();

    CropStatus getCrop(String cropCode);

    void deleteCrop(String cropCode);

    void updateCrop(String cropCode, CropDTO cropDTO);
}

package lk.ijse.greenshadowbackendapi.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.greenshadowbackendapi.customStatusCode.SelectedEntityErrorStatus;
import lk.ijse.greenshadowbackendapi.dao.CropDAO;
import lk.ijse.greenshadowbackendapi.dto.CropStatus;
import lk.ijse.greenshadowbackendapi.dto.impl.CropDTO;
import lk.ijse.greenshadowbackendapi.entity.CropEntity;
import lk.ijse.greenshadowbackendapi.exception.CropNotFoundException;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.service.CropService;
import lk.ijse.greenshadowbackendapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropServiceImpl implements CropService {

    @Autowired
    private CropDAO cropDAO ;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDTO cropDTO) throws DataPersistException {
        CropEntity savedCrop = cropDAO.save(mapping.toCropEntity(cropDTO));
        if (savedCrop == null) {
            throw new DataPersistException("Crop not saved!");
        }
    }

    @Override
    public List<CropDTO> getAllCrops(){
        List<CropEntity> allCrops = cropDAO.findAll();
        return mapping.toCropDTOList(allCrops);
    }

    @Override
    public CropStatus getCrop(String cropCode){
        if(cropDAO.existsById(cropCode)){
            CropEntity selectedCrop = cropDAO.getReferenceById(cropCode);
            return mapping.toCropDTO(selectedCrop);
        }else{
            return new SelectedEntityErrorStatus(2, "Crop with code " + cropCode + "not found!");
        }
    }

    @Override
    public void deleteCrop(String cropCode){
        if (cropDAO.existsById(cropCode)) {
            cropDAO.deleteById(cropCode);
        }else {
            throw new CropNotFoundException("Crop with id " + cropCode + " not Found");
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO){
        Optional<CropEntity> existedCrop = cropDAO.findById(cropCode);
        if (existedCrop.isPresent()) {
            existedCrop.get().setCropCommonName(cropDTO.getCropCommonName());
            existedCrop.get().setCropScientificName(cropDTO.getCropScientificName());
            existedCrop.get().setCropImage(cropDTO.getCropImage());
            existedCrop.get().setCropCategory(cropDTO.getCropCategory());
            existedCrop.get().setCropSeason(cropDTO.getCropSeason());
        }else {
            throw new CropNotFoundException("Crop with id " + cropCode + " not Found");
        }
    }

}

package lk.ijse.greenshadowbackendapi.util;

import lk.ijse.greenshadowbackendapi.dto.impl.*;
import lk.ijse.greenshadowbackendapi.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    // Crop related mappings

    // cropDTO to cropEntity mapping
    public CropEntity toCropEntity(CropDTO cropDTO){ return modelMapper.map(cropDTO, CropEntity.class); }

    // cropEntity to cropDTO mapping
    public CropDTO toCropDTO(CropEntity cropEntity){ return modelMapper.map(cropEntity, CropDTO.class); }

    // cropEntityList to cropDTOList
    public List<CropDTO> toCropDTOList(List<CropEntity> cropEntityList){
        return modelMapper.map(cropEntityList, new TypeToken<List<CropDTO>>() {}.getType());
    }

    // Field related mappings

    // fieldDTO to fieldEntity mapping
    public FieldEntity toFieldEntity(FieldDTO fieldDTO) {
        return modelMapper.map(fieldDTO, FieldEntity.class);
    }

    // fieldEntity to fieldDTO mapping
    public FieldDTO toFieldDTO(FieldEntity fieldEntity) {
        return modelMapper.map(fieldEntity, FieldDTO.class);
    }



    // fieldEntityList to fieldDTOList
    public List<FieldDTO> toFieldDTOList(List<FieldEntity> fieldEntityList) {
        return modelMapper.map(fieldEntityList, new TypeToken<List<FieldDTO>>() {}.getType());
    }

    // Staff related mapping

    // staffDTO to staffEntity mapping
    public StaffEntity toStaffEntity(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    // staffEntity to staffDTO mapping
    public StaffDTO toStaffDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDTO.class);
    }

    // staffEntityList to staffDTOList
    public List<StaffDTO> toStaffDTOList(List<StaffEntity> staffEntityList) {
        return modelMapper.map(staffEntityList, new TypeToken<List<StaffDTO>>() {}.getType());
    }

    // Equipment related mapping

    // EquipmentDTO to EquipmentEntity
    public EquipmentEntity toEquipmentEntity(EquipmentDTO equipmentDTO) {
        return modelMapper.map(equipmentDTO, EquipmentEntity.class);
    }

    // EquipmentEntity to EquipmentDTO
    public EquipmentDTO toEquipmentDTO(EquipmentEntity equipmentEntity) {
        return modelMapper.map(equipmentEntity, EquipmentDTO.class);
    }

    // EquipmentEntityList to EquipmentDTOList
    public List<EquipmentDTO> toEquipmentDTOList(List<EquipmentEntity> equipmentEntityList) {
        return modelMapper.map(equipmentEntityList, new TypeToken<List<EquipmentDTO>>() {}.getType());
    }

    // Vehicle related mapping

    // VehicleDTO to VehicleEntity
    public VehicleEntity toVehicleEntity(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, VehicleEntity.class);
    }

    // VehicleEntity to VehicleDTO
    public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }

    // VehicleEntityList to VehicleDTOList
    public List<VehicleDTO> toVehicleDTOList(List<VehicleEntity> vehicleEntityList) {
        return modelMapper.map(vehicleEntityList, new TypeToken<List<VehicleDTO>>() {}.getType());
    }

    // MonitoringLog related mapping

    // MonitoringLogDTO to MonitoringLogEntity
    public MonitoringLogEntity toMonitoringLogEntity(MonitoringLogDTO monitoringLogDTO) {
        return modelMapper.map(monitoringLogDTO, MonitoringLogEntity.class);
    }

    // MonitoringLogEntity to MonitoringLogDTO
    public MonitoringLogDTO toMonitoringLogDTO(MonitoringLogEntity monitoringLogEntity) {
        return modelMapper.map(monitoringLogEntity, MonitoringLogDTO.class);
    }

    // MonitoringLogEntityList to MonitoringLogDTOList
    public List<MonitoringLogDTO> toMonitoringLogDTOList(List<MonitoringLogEntity> monitoringLogEntityList) {
        return modelMapper.map(monitoringLogEntityList, new TypeToken<List<MonitoringLogDTO>>() {}.getType());
    }
}

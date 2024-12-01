package lk.ijse.greenshadowbackendapi.dto.impl;

import lk.ijse.greenshadowbackendapi.dto.EquipmentStatus;
import lk.ijse.greenshadowbackendapi.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements SuperDTO, EquipmentStatus {
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String status;
    private StaffDTO staff;
    private FieldDTO fields;
}

package lk.ijse.greenshadowbackendapi.dto.impl;

import lk.ijse.greenshadowbackendapi.entity.FieldEntity;
import lk.ijse.greenshadowbackendapi.entity.StaffEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO {
    private String equipmentId;
    private String equipmentName;
    private String equipmentType;
    private String status;
    private StaffEntity staff;
    private FieldEntity fields;
}

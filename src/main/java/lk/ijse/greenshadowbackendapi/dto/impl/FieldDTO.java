package lk.ijse.greenshadowbackendapi.dto.impl;

import lk.ijse.greenshadowbackendapi.dto.FieldStatus;
import lk.ijse.greenshadowbackendapi.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements SuperDTO, FieldStatus {
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private Double fieldSize;
    private String fieldImage01;
    private String fieldImage02;
    private List<CropDTO> crops;
    private List<MonitoringLogServiceDTO> logServices;
}

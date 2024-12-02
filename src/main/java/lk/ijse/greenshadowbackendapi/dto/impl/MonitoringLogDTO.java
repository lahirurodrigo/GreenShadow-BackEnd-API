package lk.ijse.greenshadowbackendapi.dto.impl;

import lk.ijse.greenshadowbackendapi.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO implements SuperDTO {
    private String logCode;
    private Date logDate;
    private String logDetails;
    private String observedImage;
    private FieldDTO fields;
    private CropDTO crop;
    private StaffDTO staff;
}

package lk.ijse.greenshadowbackendapi.dto.impl;

import lk.ijse.greenshadowbackendapi.dto.CropStatus;
import lk.ijse.greenshadowbackendapi.dto.StaffStatus;
import lk.ijse.greenshadowbackendapi.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements SuperDTO, CropStatus{
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String cropImage;
    private String cropCategory;
    private String cropSeason;
}

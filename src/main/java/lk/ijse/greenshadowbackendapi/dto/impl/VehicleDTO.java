package lk.ijse.greenshadowbackendapi.dto.impl;

import lk.ijse.greenshadowbackendapi.dto.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements VehicleStatus {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    private StaffDTO staff;
    private String assistantId;
}

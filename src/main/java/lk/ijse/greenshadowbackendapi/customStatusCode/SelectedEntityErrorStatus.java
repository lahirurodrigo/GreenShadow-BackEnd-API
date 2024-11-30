package lk.ijse.greenshadowbackendapi.customStatusCode;

import lk.ijse.greenshadowbackendapi.dto.CropStatus;
import lk.ijse.greenshadowbackendapi.dto.FieldStatus;
import lk.ijse.greenshadowbackendapi.dto.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedEntityErrorStatus implements CropStatus, FieldStatus, StaffStatus {
    private int statusCode;
    private String statusMessage;
}

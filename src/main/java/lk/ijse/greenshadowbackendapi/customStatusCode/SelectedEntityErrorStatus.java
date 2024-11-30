package lk.ijse.greenshadowbackendapi.customStatusCode;

import lk.ijse.greenshadowbackendapi.dto.CropStatus;
import lk.ijse.greenshadowbackendapi.dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedEntityErrorStatus implements CropStatus, FieldStatus {
    private int statusCode;
    private String statusMessage;
}

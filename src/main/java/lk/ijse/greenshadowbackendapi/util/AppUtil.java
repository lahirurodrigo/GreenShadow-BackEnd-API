package lk.ijse.greenshadowbackendapi.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateFieldCode() {
        return "FIELD-"+ UUID.randomUUID();
    }
    public static String fieldImage01ToBase64(byte [] fieldImage01) {
        return Base64.getEncoder().encodeToString(fieldImage01);
    }
    public static String fieldImage02ToBase64(byte [] fieldImage02) {
        return Base64.getEncoder().encodeToString(fieldImage02);
    }

    public static String cropImageToBase64(byte[] bytesCropImage) {
        return Base64.getEncoder().encodeToString(bytesCropImage);
    }

    public static String generateCropCode() {
        return "FIELD-"+ UUID.randomUUID();
    }
}

package lk.ijse.greenshadowbackendapi.exception;

public class CropNotFoundException extends RuntimeException{
    public CropNotFoundException() {
    }

    public CropNotFoundException(String message) {
        super(message);
    }

    public CropNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CropNotFoundException(Throwable cause) {
        super(cause);
    }

}

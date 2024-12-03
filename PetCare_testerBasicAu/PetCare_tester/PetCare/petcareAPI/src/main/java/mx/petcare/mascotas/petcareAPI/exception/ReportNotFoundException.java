package mx.petcare.mascotas.petcareAPI.exception;

public class ReportNotFoundException extends RuntimeException {
    
   
    public ReportNotFoundException(String message) {
        super(message);
    }

   
    public ReportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

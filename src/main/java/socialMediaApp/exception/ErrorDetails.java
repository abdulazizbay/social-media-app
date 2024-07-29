package socialMediaApp.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private String message;
    private String details;
    private LocalDateTime timestamp;


    public ErrorDetails(String message, String description, LocalDateTime now) {
    }
}

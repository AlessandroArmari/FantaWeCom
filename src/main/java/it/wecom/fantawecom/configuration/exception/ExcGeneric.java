package it.wecom.fantawecom.configuration.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public abstract class ExcGeneric extends RuntimeException {

    private String userMessage;
    private String message;
    private HttpStatus httpStatus;

    private String className;
    private String methodName;
    private String line;

    private LocalDateTime localDateTime;

    protected ExcGeneric(String userMessage, String message, String className, String methodName, String line) {
        this.userMessage = userMessage;
        this.message = message;
        this.className = className;
        this.methodName = methodName;
        this.line = line;

        //NOW
        this.localDateTime = LocalDateTime.now();
    }
}

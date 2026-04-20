package it.wecom.fantawecom.configuration.exception;


import it.wecom.fantawecom.K.Ksecurity;
import org.springframework.http.HttpStatus;

public class Exc401 extends ExcGeneric {

    public Exc401(String userMessage, String message, String className, String methodName, String line) {
        super(userMessage, message, className, methodName, line);
        this.setUserMessage(Ksecurity.UNATHORIZED);
        this.setHttpStatus(HttpStatus.UNAUTHORIZED);
    }


}

package it.wecom.fantawecom.configuration.exception;

import it.wecom.fantawecom.K.Kerror;
import it.wecom.fantawecom.K.Ksecurity;
import org.springframework.http.HttpStatus;

import static org.hibernate.boot.models.HibernateAnnotations.NOT_FOUND;


public class Exc404 extends ExcGeneric {

    public Exc404(String userMessage, String message, String className, String methodName, String line) {

        super(userMessage, message, className, methodName, line);
        this.setHttpStatus(HttpStatus.NOT_FOUND);

    }
}

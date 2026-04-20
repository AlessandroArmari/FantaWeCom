package it.wecom.fantawecom.configuration.exception;


import org.springframework.http.HttpStatus;

public class Exc404 extends ExcGeneric {

    public Exc404() {
        super(null, null, null, null, null);
        this.setHttpStatus(HttpStatus.UNAUTHORIZED);
    }


}

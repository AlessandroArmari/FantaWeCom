package it.wecom.fantawecom.configuration.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exc401.class)
    public ResponseEntity<Object> handleExc401(Exc401 ex404, WebRequest request) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex404);
    }

    @ExceptionHandler(Exc404.class)
    public ResponseEntity<Object> handleExc404(Exc404 ex404, WebRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex404);
    }

}

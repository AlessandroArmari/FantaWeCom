package it.wecom.fantawecom.configuration.exception;

import it.wecom.fantawecom.configuration.exception.ExcResDto.ExcResDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ModelMapper modelMapper;


    @ExceptionHandler(Exc401.class)
    public ResponseEntity<ExcResDto> handleExc401(Exc401 ex401, WebRequest request) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(modelMapper.map(ex401, ExcResDto.class));
    }

    @ExceptionHandler(Exc404.class)
    public ResponseEntity<ExcResDto> handleExc404(Exc404 ex404, WebRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(modelMapper.map(ex404, ExcResDto.class));

    }

}

package it.wecom.fantawecom.configuration.exception.ExcResDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcResDto {

    private String userMessage;
    private HttpStatus httpStatus;

}

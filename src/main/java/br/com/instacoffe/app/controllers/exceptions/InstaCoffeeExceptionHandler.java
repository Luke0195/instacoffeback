package br.com.instacoffe.app.controllers.exceptions;

import br.com.instacoffe.app.dtos.response.FieldResponseDto;
import br.com.instacoffe.app.dtos.response.StandardResponseErrorDto;
import br.com.instacoffe.app.services.exceptions.ResourceAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class InstaCoffeeExceptionHandler {


    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<StandardResponseErrorDto> handleEntityAlreadyExistsException(HttpServletRequest request, ResourceAlreadyExistsException exception){
        StandardResponseErrorDto responseErrorDto = makeStandardResponseErrorDto(HttpStatus.BAD_REQUEST.value(), "Resource already exists", exception.getMessage(), request.getRequestURI(), new HashSet<>());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseErrorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardResponseErrorDto> handleMethodArgumentException(HttpServletRequest request, MethodArgumentNotValidException exception){
        Set<FieldResponseDto> errors = new HashSet<>();
        exception.getFieldErrors().forEach(x -> {
            errors.add(new FieldResponseDto(x.getField(), x.getDefaultMessage()));
        });
        StandardResponseErrorDto standardResponseDto = makeStandardResponseErrorDto(HttpStatus.BAD_REQUEST.value(),
                "Hibernate Validation",
                "Please check the errors field to validate the payload",
                request.getRequestURI(),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardResponseDto);
    }



    private static StandardResponseErrorDto makeStandardResponseErrorDto(Integer status, String error, String message, String path, Set<FieldResponseDto> errors){
        return new StandardResponseErrorDto(Instant.now(), status, error, message, path,  errors);
    }
}

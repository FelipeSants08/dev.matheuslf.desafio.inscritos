package dev.matheuslf.desafio.inscritos.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public List<ValidationErrorMessage> handler(MethodArgumentNotValidException exception){
        return exception.getFieldErrors()
                .stream()
                .map(ValidationErrorMessage::new)
                .toList();

    }

    @ExceptionHandler(exception = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> handler(HttpMessageNotReadableException exception){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Campo Status e Priority não podem estar em branco");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(exception = TaskNotFound.class)
    public ResponseEntity<ErrorMessage> handler(TaskNotFound exception){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.toString(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}

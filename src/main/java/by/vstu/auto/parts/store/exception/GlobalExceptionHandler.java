package by.vstu.auto.parts.store.exception;

import by.vstu.auto.parts.store.dto.response.ErrorInfoResponseDto;
import by.vstu.auto.parts.store.exception.common.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.UncheckedIOException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorInfoResponseDto> handleNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorInfoResponseDto(
                ex.getStatus(),
                ex.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorInfoResponseDto> handleValidationException(BindException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(new ErrorInfoResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                message),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UncheckedIOException.class)
    public ResponseEntity<ErrorInfoResponseDto> handleUncheckedIOException(UncheckedIOException ex) {
        return new ResponseEntity<>(new ErrorInfoResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfoResponseDto> handleGenericException(Exception ex) {
        return new ResponseEntity<>(new ErrorInfoResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}

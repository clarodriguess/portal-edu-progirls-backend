package br.com.progirls.api.portal.exception;

import br.com.progirls.api.portal.model.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {

        ErrorResponse error = new ErrorResponse(
                500,
                "Erro interno inesperado",
                request.getRequestURI(),
                LocalDateTime.now().toString()
        );

        ex.printStackTrace();

        return ResponseEntity.status(500).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex,
            HttpServletRequest request
    ) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Argumento inválido: " + ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now().toString()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}

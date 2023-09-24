package com.foro.api.infra.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErrores {

    private record Error400DTO(String campo, String error) {

        public Error400DTO(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }

    }

    private record Error409DTO(String estado_sql, String mensaje) {

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> error400(MethodArgumentNotValidException mException) {
        var errores = mException.getFieldErrors().stream().map(Error400DTO::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> error409(SQLIntegrityConstraintViolationException sException) {
        return ResponseEntity.status(409).body(new Error409DTO(sException.getSQLState(), sException.getMessage()));
    }

}

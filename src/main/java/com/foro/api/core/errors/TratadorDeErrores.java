package com.foro.api.core.errors;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErrores {

    private record ErrorDTO(int codigo, String mensaje) {

    }

    private record Error400DTO(String campo, String error) {

        public Error400DTO(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }

    }

    private record Error409DTO(@JsonAlias("estado_sql") String estadoSql, String mensaje) {

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity<?> error404(ValidacionDeIntegridad e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(404, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> error400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(Error400DTO::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> error409(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.status(409).body(new Error409DTO(e.getSQLState(), e.getMessage()));
    }

}

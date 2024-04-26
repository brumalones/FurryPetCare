package br.com.furrypetcare.controller.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Handlers {

    //Tratamento para autenticação
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity BadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials entered are invalid");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity AuthenticationException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials entered are invalid");
    }

    // Tratamento para BadRequest: retorna os campos obrigatorios nos formularios
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity ArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorData::new).toList());
    }

    //Tratamento para Exception não tratadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity InternalServerError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getLocalizedMessage());
    }

    //Tratamento para requisições sem conteudo
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity MessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Required request body is missing.");
    }

    //Tratamento para objeto não encontrado
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity NotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found.");
    }

    // Tratamentos não validados
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity tratarErroAcessoNegado() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidatorException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }



}

package br.com.carlos.api.exception;

import br.com.carlos.api.exception.duplicate.DuplicateEmailException;
import br.com.carlos.api.exception.duplicate.DuplicateFiedException;
import br.com.carlos.api.exception.duplicate.DuplicatePhoneException;
import br.com.carlos.api.exception.duplicate.DuplicateUserNameException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateUserNameException.class)
    public ResponseEntity<?> existsUserName(DuplicateUserNameException userNameException) {
        return ResponseEntity.status(409)
                .body(userNameException.getMessage());
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<?> existEmail(DuplicateEmailException duplicateEmailException) {
        return ResponseEntity.status(409)
                .body(duplicateEmailException.getMessage());
    }

    @ExceptionHandler(DuplicatePhoneException.class)
    public ResponseEntity<?> existsPhone(DuplicatePhoneException duplicatePhoneException) {
        return ResponseEntity.status(409)
                .body(duplicatePhoneException.getMessage());
    }

    @ExceptionHandler(ValidException.class)
    public ResponseEntity<?> notValid(ValidException validException) {
        return ResponseEntity.status(401)
                .body(validException.getMessage());
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<?> idNotFound(IdNotFoundException idNotFoundException) {
        return ResponseEntity.status(404)
                .body(idNotFoundException.getMessage());
    }

    @ExceptionHandler(DuplicateFiedException.class)
    public ResponseEntity<?> duplicateFildException(DuplicateFiedException duplicateFiedException) {
        return ResponseEntity.status(409)
                .body(duplicateFiedException.getMessage());
    }

}

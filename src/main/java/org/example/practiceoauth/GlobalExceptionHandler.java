package org.example.practiceoauth;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<BaseResponse<String>> handle(ResponseStatusException ex){
        return ResponseEntity.status(ex.getStatusCode()).body(new BaseResponse<>(false, ex.getReason(), null));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse<String>> handle(ConstraintViolationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<BaseResponse<String>> handle(AuthenticationServiceException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BaseResponse<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(false, "Internal Server Error", null));
    }
}

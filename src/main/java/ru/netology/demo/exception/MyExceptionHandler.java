package ru.netology.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnauthorizedUser.class)
    protected ResponseEntity<MessageException> handleUnauthorizedUser() {
        return new ResponseEntity<>(new MessageException("Unknown user"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidCredentials.class)
    protected ResponseEntity<MessageException> handleInvalidCredentials() {
        return new ResponseEntity<>(new MessageException("User name or password is empty"), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    private static class MessageException {
        private String message;
    }
}
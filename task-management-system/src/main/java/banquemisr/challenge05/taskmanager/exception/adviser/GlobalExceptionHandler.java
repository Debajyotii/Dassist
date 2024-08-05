package banquemisr.challenge05.taskmanager.exception.adviser;

import banquemisr.challenge05.taskmanager.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import javax.security.auth.login.FailedLoginException;
import java.util.HashMap;
import java.util.Map;

import static banquemisr.challenge05.taskmanager.util.AppConstants.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new HashMap<>();
        String[] exp = ex.getLocalizedMessage().split(":");
        if (exp.length > 1)
            errors.put(MSG, exp[1]);
        else
            errors.put(MSG, ex.getLocalizedMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FailedLoginException.class)
    public ResponseEntity<String> handleExceptions(FailedLoginException ex) {
        log.error(ERROR, ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<String> handleExceptions(AuthorizationDeniedException ex) {
        log.error(ERROR, ex);
        return new ResponseEntity<>("PERMISSION NOT GIVEN", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleExceptions(NoResourceFoundException ex) {
        log.error(ERROR, ex);
        return new ResponseEntity<>("Facing Some Issue !! Seems path is not correct", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleExceptions(CustomException ex) {
        log.error(ERROR, ex);
        if (TASK_NOT_FOUND.equals(ex.getMessage()))
            return new ResponseEntity<>(TASK_NOT_FOUND, HttpStatus.OK);
        else
            return new ResponseEntity<>("Facing Some Issue !!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleExceptions(HttpRequestMethodNotSupportedException ex) {
        log.error(ERROR, ex);
        return new ResponseEntity<>("See http method not correct", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<String> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return new ResponseEntity<>("Unsupported Media Type. Please use application/json.", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleExceptions(DataIntegrityViolationException ex) {
        log.error(ERROR, ex);
        return new ResponseEntity<>("User Name already present !!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex) {
        log.error(ERROR, ex);
        return new ResponseEntity<>("Facing Some Issue !!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

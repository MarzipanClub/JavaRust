package club.marzipan.javacarrentals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.error(ex.getMessage());
        return ResponseEntity.internalServerError().contentType(MediaType.valueOf("text/plain; charset=utf-8"))
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationExceptions(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, List<String>> errors = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach((field_error) -> {
            List<String> messages = errors.computeIfAbsent(field_error.getField(), field -> new ArrayList<>());
            messages.add(field_error.getDefaultMessage());
        });
        logger.warn(methodArgumentNotValidException.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
}
package com.matthew.schedule.Controller;

import com.matthew.schedule.exceptions.ActivityNotFoundException;
import com.matthew.schedule.exceptions.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {ActivityNotFoundException.class})
    public ResponseEntity<ErrorDto> handleActivityNotFoundException(ActivityNotFoundException e) {
        log.info("Activity not found.", e);
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorDto error = new ErrorDto("Activity not found", details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

package com.ltp.gradesubmission.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ltp.gradesubmission.application.exception.CourseNotFoundException;
import com.ltp.gradesubmission.application.exception.GradeNotFoundException;
import com.ltp.gradesubmission.application.exception.StudentNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
        StudentNotFoundException.class,
        CourseNotFoundException.class,
        GradeNotFoundException.class
    })

    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}

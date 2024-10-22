package org.sopt.diary.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(ContentLengthException.class)
    public ResponseEntity<String> handleContentLengthException(final ContentLengthException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(InputTitleExcpetion.class)
    public ResponseEntity<String> handleContentLengthException(final InputTitleExcpetion e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}

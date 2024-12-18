package org.sopt.diary.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ExceptionHandler(WrongIdException.class)
    public ResponseEntity<String> handleWrongIdException(final WrongIdException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NotFoundDiaryException.class)
    public ResponseEntity<String> handleNotFoundDiaryException(final NotFoundDiaryException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(WriteTimeShortException.class)
    public ResponseEntity<String> handleWriteTimeShortException(final WriteTimeShortException e) {
        return ResponseEntity.status(429).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest().body("Invalid Parameter Type");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Not Variable Input Text");
    }


}

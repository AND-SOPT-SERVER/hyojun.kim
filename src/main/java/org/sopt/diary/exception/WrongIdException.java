package org.sopt.diary.exception;

public class WrongIdException extends RuntimeException {
    public WrongIdException() {
        super("Invalid id");
    }

}

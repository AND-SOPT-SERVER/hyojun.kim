package org.sopt.diary.exception;

public class ContentLengthException extends RuntimeException{
    public ContentLengthException() {
        super("Content Length is not valid");
    }

}

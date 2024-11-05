package org.sopt.diary.exception;

public class WrongUserAccessException extends RuntimeException{

    public WrongUserAccessException() {
        super("잘못된 사용자 접근입니다.");
    }
}

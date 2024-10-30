package org.sopt.diary.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(Long userId) {
        super("해당 유저를 찾을 수 없습니다. userId: " + userId);
    }

}

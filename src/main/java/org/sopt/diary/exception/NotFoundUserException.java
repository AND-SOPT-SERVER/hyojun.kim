package org.sopt.diary.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(Long userId) {
        super("해당 유저를 id로 찾을 수 없습니다. userId: " + userId);
    }


    public NotFoundUserException(String username) {
        super("해당 유저를 이름으로 찾을 수 없습니다. username: " + username);
    }


}

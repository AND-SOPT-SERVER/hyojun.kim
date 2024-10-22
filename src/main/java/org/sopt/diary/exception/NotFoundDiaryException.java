package org.sopt.diary.exception;

public class NotFoundDiaryException extends RuntimeException{

    public NotFoundDiaryException(Long id) {
        super("Not Found Diary" + id);
    }
}

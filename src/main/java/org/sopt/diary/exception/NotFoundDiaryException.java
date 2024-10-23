package org.sopt.diary.exception;

public class NotFoundDiaryException extends RuntimeException{

    public NotFoundDiaryException() {
        super("Not Found Diary");
    }

    public NotFoundDiaryException(Long id) {
        super("Not Found Diary" + id);
    }
}

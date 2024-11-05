package org.sopt.diary.exception;

import java.time.LocalDateTime;

public class WriteTimeShortException extends RuntimeException{

    public WriteTimeShortException(final LocalDateTime createdAt) {
        super("The write time is too short. You can write again at " + createdAt.toLocalTime() );
    }

}

package org.sopt.diary.service;

import org.sopt.diary.domain.Diary;
import org.sopt.diary.exception.WriteTimeShortException;
import org.springframework.stereotype.Component;

@Component
public class WriteTimeChecker {

    private final DateTimeUtil dateTimeUtil;

    public WriteTimeChecker(DateTimeUtil dateTimeUtil) {
        this.dateTimeUtil = dateTimeUtil;
    }

    public void isValidTimeToWriteDiary(final Diary diary) {
        if(dateTimeUtil.isValidTimeToWriteDiary(diary.getCreatedAt()))
            throw new WriteTimeShortException(diary.getCreatedAt().plusMinutes(5));
    }

}

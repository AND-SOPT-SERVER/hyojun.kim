package org.sopt.diary.api.response;

import java.time.LocalDate;
import org.sopt.diary.domain.Diary;

public record MyDiaryResponse(String title, LocalDate createdAt) {


    public static MyDiaryResponse of(final Diary diary) {
        return new MyDiaryResponse(diary.getTitle(), diary.getCreatedAt().toLocalDate());
    }

}

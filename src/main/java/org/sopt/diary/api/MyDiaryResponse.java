package org.sopt.diary.api;

import java.time.LocalDateTime;
import org.sopt.diary.domain.Diary;

public record MyDiaryResponse(String title, LocalDateTime createdAt) {


    public static MyDiaryResponse of(final Diary diary) {
        return new MyDiaryResponse(diary.getTitle(), diary.getCreatedAt());
    }

}

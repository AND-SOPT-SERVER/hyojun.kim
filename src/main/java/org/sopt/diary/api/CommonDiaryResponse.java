package org.sopt.diary.api;

import java.time.LocalDateTime;
import org.sopt.diary.domain.Diary;

public record CommonDiaryResponse(String title, String username, LocalDateTime createdAt) {

    public static CommonDiaryResponse of(final Diary diary) {
        return new CommonDiaryResponse(diary.getTitle(), diary.getUser().getUserName(), diary.getCreatedAt());
    }
}

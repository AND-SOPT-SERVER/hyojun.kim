package org.sopt.diary.api.response;

import java.time.LocalDate;
import org.sopt.diary.domain.Diary;

public record CommonDiaryResponse(String title, String username, LocalDate createdAt) {

    public static CommonDiaryResponse of(final Diary diary) {
        return new CommonDiaryResponse(diary.getTitle(), diary.getUser().getUserName(), diary.getCreatedAt().toLocalDate());
    }
}

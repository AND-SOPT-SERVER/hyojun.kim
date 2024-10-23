package org.sopt.diary.api;

import java.time.LocalDateTime;
import org.sopt.diary.service.Diary;

public record DetailDiaryResponse(Long id, String title, String content, LocalDateTime createdAt) {

    public static DetailDiaryResponse of(final Diary diary) {
        return new DetailDiaryResponse(diary.getId(), diary.getTitle(), diary.getContent(),
            diary.getCreatedAt());
    }
}

package org.sopt.diary.api;

import java.time.LocalDateTime;
import org.sopt.diary.repository.constant.Category;
import org.sopt.diary.domain.Diary;

public record DetailDiaryResponse(Long id, String title, String content, LocalDateTime createdAt, Category category) {

    public static DetailDiaryResponse of(final Diary diary) {
        return new DetailDiaryResponse(diary.getId(), diary.getTitle(), diary.getContent(),
            diary.getCreatedAt(),diary.getCategory()) ;
    }
}

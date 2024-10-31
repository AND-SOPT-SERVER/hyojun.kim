package org.sopt.diary.api.response;

import java.time.LocalDate;
import org.sopt.diary.repository.constant.Category;
import org.sopt.diary.domain.Diary;

public record DetailDiaryResponse(String title, String content, LocalDate createdAt, Category category) {

    public static DetailDiaryResponse of(final Diary diary) {
        return new DetailDiaryResponse(diary.getTitle(), diary.getContent(),
            diary.getCreatedAt().toLocalDate(),diary.getCategory()) ;
    }
}

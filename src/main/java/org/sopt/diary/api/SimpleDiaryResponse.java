package org.sopt.diary.api;

import org.sopt.diary.service.Diary;

public record SimpleDiaryResponse(Long id, String name) {

    public static SimpleDiaryResponse of(final Diary diary) {
        return new SimpleDiaryResponse(diary.getId(), diary.getTitle());
    }
}

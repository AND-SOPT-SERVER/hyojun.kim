package org.sopt.diary.api;

import org.sopt.diary.service.Diary;

public record DiaryResponse(Long id, String name) {

    public static DiaryResponse of(final Diary diary) {
        return new DiaryResponse(diary.getId(), diary.getName());
    }
}

package org.sopt.diary.api;

import java.util.List;

public record DiaryListResponse(List<DiaryResponse> diaryList) {

    public static DiaryListResponse of(final List<DiaryResponse> diaryList) {
        return new DiaryListResponse(diaryList);
    }


    public List<DiaryResponse> getDiaryList() {
        return diaryList;
    }
}

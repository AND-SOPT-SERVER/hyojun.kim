package org.sopt.diary.api;

import java.util.List;

public record DiaryListResponse(List<SimpleDiaryResponse> diaryList) {

    public static DiaryListResponse of(final List<SimpleDiaryResponse> diaryList) {
        return new DiaryListResponse(diaryList);
    }


    public List<SimpleDiaryResponse> getDiaryList() {
        return diaryList;
    }
}

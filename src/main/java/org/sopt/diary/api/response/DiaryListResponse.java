package org.sopt.diary.api.response;

import java.util.List;

public record DiaryListResponse(List<CommonDiaryResponse> diaryList) {

    public static DiaryListResponse of(final List<CommonDiaryResponse> diaryList) {
        return new DiaryListResponse(diaryList);
    }



    public List<CommonDiaryResponse> getDiaryList() {
        return diaryList;
    }
}

package org.sopt.diary.api;

import java.util.List;

public record CommonDiaryListResponse(List<CommonDiaryResponse> diaryList) {

    public static CommonDiaryListResponse of(final List<CommonDiaryResponse> diaryList) {
        return new CommonDiaryListResponse(diaryList);
    }

    public List<CommonDiaryResponse> getDiaryList() {
        return diaryList;
    }

}

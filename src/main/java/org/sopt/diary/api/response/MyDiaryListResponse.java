package org.sopt.diary.api.response;

import java.util.List;

public record MyDiaryListResponse(List<MyDiaryResponse> diaryList) {

    public static MyDiaryListResponse of(final List<MyDiaryResponse> diaryList) {
        return new MyDiaryListResponse(diaryList);
    }


    public List<MyDiaryResponse> getDiaryList() {
        return diaryList;
    }
}

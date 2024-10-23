package org.sopt.diary.api;

import java.util.List;
import org.sopt.diary.service.Diary;

public interface DiaryService {

    List<SimpleDiaryResponse> findDiaryList();

    Diary findDiaryById(final Long id);

    void createDiary(final DiaryRequest diaryRequest);

    void updateDiary(final Long id, final DiaryRequest diaryRequest);

    void deleteDiary(final Long id);
}


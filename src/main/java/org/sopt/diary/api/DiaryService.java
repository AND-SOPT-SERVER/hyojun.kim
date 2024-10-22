package org.sopt.diary.api;

import java.util.List;
import org.sopt.diary.service.Diary;

public interface DiaryService {

    List<Diary> findDiaryList();

    DiaryResponse findDiaryById(final Long id);

    void createDiary(final DiaryRequest diaryRequest);

    DiaryResponse updateDiary(final Long id, final DiaryRequest diaryRequest);

    Boolean deleteDiary(final Long id);
}


package org.sopt.diary.api;

import java.util.List;
import org.sopt.diary.repository.constant.Category;
import org.sopt.diary.service.Diary;

public interface DiaryService {

    List<SimpleDiaryResponse> findDiaryList();

    List<SimpleDiaryResponse> findDiaryListByCategory(final Category category);

    Diary findDiaryById(final Long id);

    void createDiary(final DiaryRequest diaryRequest);

    void updateDiary(final Long id, final DiaryRequest diaryRequest);

    void deleteDiary(final Long id);
}


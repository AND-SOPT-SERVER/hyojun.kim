package org.sopt.diary.api;

import java.util.List;
import org.sopt.diary.api.request.DiaryRequest;
import org.sopt.diary.api.response.CommonDiaryResponse;
import org.sopt.diary.api.response.MyDiaryResponse;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.constant.Category;
import org.sopt.diary.domain.Diary;
import org.sopt.diary.util.constant.Sort;

public interface DiaryService {

    List<CommonDiaryResponse> findDiaryList(Sort sort);

    List<MyDiaryResponse> findMyDiaryList(Long userId, Sort sort);

    List<CommonDiaryResponse> findDiaryListByCategory(final Category category, final Sort sort);

    List<MyDiaryResponse> findMyDiaryListByCategory(final Long userId, final Category category, final Sort sort);

    Diary findDiaryById(final Long id);

    void createDiary(final DiaryRequest diaryRequest, final UserEntity userEntity);

    void updateDiary(final Long id, final DiaryRequest diaryRequest);

    void deleteDiary(final Long id);
}


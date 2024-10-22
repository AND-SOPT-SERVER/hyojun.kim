package org.sopt.diary.service;

import java.util.Comparator;
import java.util.List;
import org.sopt.diary.api.DiaryRequest;
import org.sopt.diary.api.DiaryResponse;
import org.sopt.diary.api.DiaryService;
import org.sopt.diary.exception.NotFoundDiaryException;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

@Component
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryServiceImpl(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }


    @Override
    public void createDiary(final DiaryRequest diaryRequest) {
        diaryRepository.save(new DiaryEntity(diaryRequest.title(), diaryRequest.content()));
    }


    @Override
    public List<DiaryResponse> findDiaryList() {
        return diaryRepository.findAll().stream()
            .map(Diary::of)
            .map(DiaryResponse::of)
            .sorted(Comparator.comparing(DiaryResponse::id))
            .toList();
    }

    @Override
    public Diary findDiaryById(Long id) {
        return Diary.from(
            diaryRepository.findById(id).orElseThrow(() -> new NotFoundDiaryException(id)));
    }

    @Override
    public DiaryResponse updateDiary(Long id, DiaryRequest diaryRequest) {
        return null;
    }

    @Override
    public Boolean deleteDiary(Long id) {
        return null;
    }
}

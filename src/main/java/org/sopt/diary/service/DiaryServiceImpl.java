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
import org.springframework.transaction.annotation.Transactional;

@Component
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryServiceImpl(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }


    @Override
    @Transactional
    public void createDiary(final DiaryRequest diaryRequest) {
        diaryRepository.save(new DiaryEntity(diaryRequest.title(), diaryRequest.content()));
    }


    @Override
    @Transactional(readOnly = true)
    public List<DiaryResponse> findDiaryList() {
        return diaryRepository.findAll().stream()
            .map(Diary::of)
            .map(DiaryResponse::of)
            .sorted(Comparator.comparing(DiaryResponse::id))
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Diary findDiaryById(Long id) {
        return Diary.from(
            diaryRepository.findById(id).orElseThrow(() -> new NotFoundDiaryException(id)));
    }

    @Override
    @Transactional
    public void updateDiary(Long id, DiaryRequest diaryRequest) {
        DiaryEntity diaryEntity = diaryRepository.findById(id)
            .orElseThrow(() -> new NotFoundDiaryException(id));

        diaryEntity.update(diaryRequest.title(), diaryRequest.content());
    }

    @Override
    @Transactional
    public Boolean deleteDiary(Long id) {
        return null;
    }
}

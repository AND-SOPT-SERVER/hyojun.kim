package org.sopt.diary.service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.sopt.diary.api.DiaryRequest;
import org.sopt.diary.api.SimpleDiaryResponse;
import org.sopt.diary.api.DiaryService;
import org.sopt.diary.util.validator.TitleValidator;
import org.sopt.diary.exception.InputTitleExcpetion;
import org.sopt.diary.exception.NotFoundDiaryException;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    private final DateTimeUtil dateTimeUtil;
    private final WriteTimeChecker writeTimeChecker;
    private final TitleValidator titleValidator;


    public DiaryServiceImpl(DiaryRepository diaryRepository, DateTimeUtil dateTimeUtil,
        WriteTimeChecker writeTimeChecker, TitleValidator titleValidator) {
        this.diaryRepository = diaryRepository;
        this.dateTimeUtil = dateTimeUtil;
        this.writeTimeChecker = writeTimeChecker;
        this.titleValidator = titleValidator;
    }



    @Override
    @Transactional
    public void createDiary(final DiaryRequest diaryRequest) {
        diaryRepository.findLastDiary()
            .ifPresent(lastDiary -> writeTimeChecker.isValidTimeToWriteDiary(Diary.of(lastDiary)));

        diaryRepository.save(
            DiaryEntity.of(diaryRequest.title(), diaryRequest.content(), dateTimeUtil.nowTime()
            ));
    }


    @Override
    @Transactional(readOnly = true)
    public List<SimpleDiaryResponse> findDiaryList() {
        return diaryRepository.findAll().stream()
            .map(Diary::of)
            .map(SimpleDiaryResponse::of)
            .sorted(Comparator.comparing(SimpleDiaryResponse::id).reversed())
            .limit(10)
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
    public void deleteDiary(Long id) {
        DiaryEntity findDiaryEntity = diaryRepository.findById(id)
            .orElseThrow(() -> new NotFoundDiaryException(id));

        diaryRepository.delete(findDiaryEntity);
    }
}

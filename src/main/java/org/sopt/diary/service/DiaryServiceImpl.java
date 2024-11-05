package org.sopt.diary.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.sopt.diary.api.DiaryRequest;
import org.sopt.diary.api.SimpleDiaryResponse;
import org.sopt.diary.api.DiaryService;
import org.sopt.diary.exception.InputTitleExcpetion;
import org.sopt.diary.exception.NotFoundDiaryException;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.sopt.diary.repository.constant.Category;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    private final DateTimeUtil dateTimeUtil;
    private final WriteTimeChecker writeTimeChecker;


    public DiaryServiceImpl(DiaryRepository diaryRepository, DateTimeUtil dateTimeUtil,
        WriteTimeChecker writeTimeChecker) {
        this.diaryRepository = diaryRepository;
        this.dateTimeUtil = dateTimeUtil;
        this.writeTimeChecker = writeTimeChecker;
    }

    @Override
    @Transactional
    public void createDiary(final DiaryRequest diaryRequest) {
        diaryRepository.findLastDiary()
            .ifPresent(lastDiary -> writeTimeChecker.isValidTimeToWriteDiary(Diary.of(lastDiary)));

        Category category = Category.convertToCategory(diaryRequest.category());

        try {
            diaryRepository.save(
                DiaryEntity.of(diaryRequest.title(), diaryRequest.content(), dateTimeUtil.nowTime(),
                    category
                ));
        }catch (DataIntegrityViolationException e){
            throw new InputTitleExcpetion();
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<SimpleDiaryResponse> findDiaryList() {
        return diaryRepository.findAll().stream()
            .map(Diary::of)
            .sorted(Comparator.comparing(Diary::getCreatedAt).reversed())
            .map(SimpleDiaryResponse::of)
            .limit(10)
            .toList();
    }

    @Override
    public List<SimpleDiaryResponse> findDiaryListByCategory(Category category) {
        return diaryRepository.findDiaryEntitiesByCategory(category)
            .stream()
            .map(Diary::of)
            .sorted(Comparator.comparing(Diary::getCreatedAt).reversed())
            .map(SimpleDiaryResponse::of)
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
        if(Objects.isNull(diaryRequest.category()))
            diaryEntity.update(diaryRequest.title(), diaryRequest.content());
        else{
            Category category = Category.convertToCategory(diaryRequest.category());
            diaryEntity.update(diaryRequest.title(), diaryRequest.content(), category);
        }
    }


    @Override
    @Transactional
    public void deleteDiary(Long id) {
        DiaryEntity findDiaryEntity = diaryRepository.findById(id)
            .orElseThrow(() -> new NotFoundDiaryException(id));

        diaryRepository.delete(findDiaryEntity);
    }
}

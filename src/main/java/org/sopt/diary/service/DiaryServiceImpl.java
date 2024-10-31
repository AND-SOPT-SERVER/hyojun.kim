package org.sopt.diary.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.sopt.diary.api.request.DiaryRequest;
import org.sopt.diary.api.response.CommonDiaryResponse;
import org.sopt.diary.api.DiaryService;
import org.sopt.diary.api.response.MyDiaryResponse;
import org.sopt.diary.util.constant.Sort;
import org.sopt.diary.domain.Diary;
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
    public List<CommonDiaryResponse> findDiaryList(Sort sort) {
        return diaryRepository.findAll().stream()
            .filter(DiaryEntity::getVisible)
            .map(Diary::of)
            .sorted(Comparator.comparing(Diary::getCreatedAt).reversed())
            .map(CommonDiaryResponse::of)
            .limit(10)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MyDiaryResponse> findMyDiaryList(Long userId, Sort sort) {
        if(sort == Sort.CREATED)
            return getMyDiaryResponsesSortedByCreatedAt(userId);
        if(sort == Sort.LENGTH)
            return getMyDiaryResponsesSortedByLength(userId);

        return getMyDiaryResponsesSortedByLength(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MyDiaryResponse> findMyDiaryListByCategory(Long userId, Category category,
        Sort sort) {
        if(sort == Sort.CREATED)
            return getMyDiaryResponsesCategoryAndSortedByCreatedAt(userId, category);
        if(sort == Sort.LENGTH)
            return getMyDiaryResponsesCategorySortedByLength(userId, category);

        throw new IllegalArgumentException("Invalid sort type");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommonDiaryResponse> findDiaryListByCategory(Category category,
        Sort sort) {
        if(sort == Sort.CREATED)
            return getDiaryResponsesCategoryAndSortedByCreatedAt(category);
        if(sort == Sort.LENGTH)
            return getDiaryResponsesCategorySortedByLength(category);

        return List.of();
    }

    private List<CommonDiaryResponse> getDiaryResponsesCategorySortedByLength(Category category) {
        return diaryRepository.findDiaryEntitiesByCategory(category).stream()
            .map(Diary::of)
            .sorted(Comparator.comparing(Diary::getContentLength).reversed())
            .map(CommonDiaryResponse::of)
            .limit(10)
            .toList();
    }

    private List<CommonDiaryResponse> getDiaryResponsesCategoryAndSortedByCreatedAt(
        Category category) {
        return diaryRepository.findDiaryEntitiesByCategory(category).stream()
            .map(Diary::of)
            .sorted(Comparator.comparing(Diary::getCreatedAt).reversed())
            .map(CommonDiaryResponse::of)
            .limit(10)
            .toList();
    }


    private List<MyDiaryResponse> getMyDiaryResponsesCategorySortedByLength(Long userId,
        Category category) {
        return diaryRepository.findDiaryEntitiesByUserIdAndCategory(userId,category).stream()
            .map(Diary::of)
            .sorted(Comparator.comparing(Diary::getContentLength).reversed())
            .map(MyDiaryResponse::of)
            .limit(10)
            .toList();
    }

    private List<MyDiaryResponse> getMyDiaryResponsesCategoryAndSortedByCreatedAt(Long userId,
        Category category) {
        return diaryRepository.findDiaryEntitiesByUserIdAndCategory(userId,category).stream()
            .map(Diary::of)
            .sorted(Comparator.comparing(Diary::getCreatedAt).reversed())
            .map(MyDiaryResponse::of)
            .limit(10)
            .toList();
    }



    private List<MyDiaryResponse> getMyDiaryResponsesSortedByCreatedAt(Long userId) {
        return diaryRepository.findAllByUser(userId).stream()
            .map(Diary::of)
            .sorted(Comparator.comparing(Diary::getCreatedAt).reversed())
            .map(MyDiaryResponse::of)
            .limit(10)
            .toList();
    }

    private List<MyDiaryResponse> getMyDiaryResponsesSortedByLength(Long userId) {
        return diaryRepository.findAllByUser(userId).stream()
            .map(Diary::of)
            .sorted(Comparator.comparing(Diary::getContentLength).reversed())
            .map(MyDiaryResponse::of)
            .limit(10)
            .toList();
    }

//    @Override
//    public List<CommonDiaryResponse> findDiaryListByCategory(Category category) {
//        return diaryRepository.findDiaryEntitiesByCategory(category)
//            .stream()
//            .map(Diary::of)
//            .sorted(Comparator.comparing(Diary::getCreatedAt).reversed())
//            .map(CommonDiaryResponse::of)
//            .limit(10)
//            .toList();
//    }

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

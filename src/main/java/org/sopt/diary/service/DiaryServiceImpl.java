package org.sopt.diary.service;

import java.util.ArrayList;
import java.util.List;
import org.sopt.diary.api.DiaryRequest;
import org.sopt.diary.api.DiaryResponse;
import org.sopt.diary.api.DiaryService;
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
    public void createDiary(final DiaryRequest diaryRequest){
        diaryRepository.save(new DiaryEntity(diaryRequest.title(), diaryRequest.content()));
    }


    @Override
    public List<Diary> findDiaryList(){
        final List<DiaryEntity> diaryEntityList = diaryRepository.findAll();
        final List<Diary> diaryList = new ArrayList<>();
        for(DiaryEntity diaryEntity : diaryEntityList){
            diaryList.add(new Diary(diaryEntity.getId(), diaryEntity.getName()));
        }
        return diaryList;
    }

    @Override
    public Diary findDiaryById(Long id) {
        return null;
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

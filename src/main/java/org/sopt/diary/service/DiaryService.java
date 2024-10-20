package org.sopt.diary.service;

import java.util.ArrayList;
import java.util.List;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.DiaryRepository;
import org.springframework.stereotype.Component;

@Component
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void createDiary(){
        diaryRepository.save(new DiaryEntity("khyojun"));
    }


    public List<Diary> getList(){
        final List<DiaryEntity> diaryEntityList = diaryRepository.findAll();


        final List<Diary> diaryList = new ArrayList<>();

        for(DiaryEntity diaryEntity : diaryEntityList){
            diaryList.add(new Diary(diaryEntity.getId(), diaryEntity.getName()));
        }



        return diaryList;
    }






}

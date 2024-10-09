package org.sopt.seminar1;


import java.util.List;

public class DiaryService {

    private final DiaryRepository diaryRepository = new DiaryRepository();

    void save(final String body){
        Diary diary = new Diary(null, body);
        diaryRepository.save(diary);
    }

    List<Diary> findAll(){
        return diaryRepository.findAll();
    }

    void patch(final Long id, final String body){
        diaryRepository.patch(id, body);
    }

    void delete(Long id){
        diaryRepository.delete(id);
    }


}
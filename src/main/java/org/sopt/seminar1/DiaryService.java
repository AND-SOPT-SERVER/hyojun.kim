package org.sopt.seminar1;


import java.time.LocalDate;
import java.util.List;

public class DiaryService {

    private final DiaryRepository diaryRepository = new DiaryRepository();

    void save(final String body){
        diaryRepository.save(body);
    }

    List<Diary> findAll(){
        return diaryRepository.findAll();
    }

    void patch(final Long id, final String body){
        diaryRepository.patch(id, body);
    }

    void restore(){
        diaryRepository.restore();
    }

    void delete(Long id){
        diaryRepository.delete(id);
    }


}
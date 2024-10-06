package org.sopt.seminar1;

import java.util.List;

public class DiaryService {
    private final DiaryRepository diaryRepository = new DiaryRepository();

    public void save(final String body){
        Diary diary = new Diary(null, body);
        if(body.length() > 30) {// 이모지 관련 처리
            System.out.println("문자가 30자가 넘을 수 없습니다!");
            throw new IllegalArgumentException("30자 이하로 입력해주세요.");
        }
        diaryRepository.save(diary);
    }

    public List<Diary> findAll(){
        return diaryRepository.findAll();
    }

    public void patch(Long id, String comment){
        diaryRepository.patch(id, comment);
    }


    public void delete(Long id){
        diaryRepository.delete(id);
    }


}
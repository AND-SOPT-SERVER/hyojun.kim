package org.sopt.seminar1;


import java.time.LocalDate;
import java.util.List;

public class DiaryService {

    private static final int LIMIT_MODIFY_COUNT = 2;
    private final DiaryRepository diaryRepository = new DiaryRepository();
    private LocalDate nowDate = LocalDate.now();
    private int modifyCount = 0;

    void save(final String body) {
        setByNewDate();
        diaryRepository.save(body);
    }

    List<Diary> findAll() {
        return diaryRepository.findAll();
    }

    void patch(final Long id, final String body) {
        setByNewDate();
        validateModifyCount();
        diaryRepository.patch(id, body);
        modifyCount++;
    }

    private void validateModifyCount() {
        if (modifyCount >= LIMIT_MODIFY_COUNT) {
            throw new IllegalArgumentException("수정은 최대 2회까지 가능합니다!");
        }
    }

    private void setByNewDate() {
        if (nowDate.isBefore(LocalDate.now())) {
            nowDate = LocalDate.now();
            modifyCount = 0;
        }
    }

    void restore() {
        setByNewDate();
        diaryRepository.restore();
    }

    void delete(Long id) {
        setByNewDate();
        diaryRepository.delete(id);
    }


}
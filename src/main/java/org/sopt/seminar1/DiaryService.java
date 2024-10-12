package org.sopt.seminar1;


import java.time.LocalDate;
import java.util.List;

public class DiaryService {

    private static final int LIMIT_MODIFY_COUNT = 2;
    private final DiaryRepository diaryRepository = new DiaryFileRepository();
    private ModifyInfo modifyInfo = diaryRepository.getModifyInfo();

    void save(final String body) {
        settingModifyInfo();
        diaryRepository.save(body);
    }

    List<Diary> findAll() {
        settingModifyInfo();
        return diaryRepository.findAll();
    }

    void patch(final Long id, final String body) {
        settingModifyInfo();
        validateModifyCount(modifyInfo);
        diaryRepository.patch(id, body, modifyInfo);
    }

    void restore() {
        settingModifyInfo();
        diaryRepository.restore();
    }

    void delete(Long id) {
        settingModifyInfo();
        diaryRepository.delete(id);
    }


    private void settingModifyInfo() {
        initModifyInfo();
        setByNewDate(modifyInfo);
    }

    private void initModifyInfo(){
        modifyInfo = diaryRepository.getModifyInfo();
    }

    private void validateModifyCount(ModifyInfo modifyInfo) {
        if (modifyInfo.getModifyCount() >= LIMIT_MODIFY_COUNT) {
            throw new IllegalArgumentException("수정은 최대 2회까지 가능합니다!");
        }
    }

    private void setByNewDate(ModifyInfo modifyInfo) {
        if (modifyInfo.getDate().isBefore(LocalDate.now())) {
            modifyInfo.setDate(LocalDate.now());
            modifyInfo.resetModifyCount();
        }
    }


}
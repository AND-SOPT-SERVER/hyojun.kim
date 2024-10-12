package org.sopt.seminar1;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository {
    void save(final String body);

    List<Diary> findAll();

    void restore();

    void delete(Long id);

    ModifyInfo getModifyInfo();

    void patch(Long id, String body, ModifyInfo modifyInfo);


}

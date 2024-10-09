package org.sopt.seminar1;

import java.util.List;

public interface DiaryRepository {
    void save(final String body);

    List<Diary> findAll();

    void patch(final Long id, final String body);

    void restore();

    void delete(Long id);

}

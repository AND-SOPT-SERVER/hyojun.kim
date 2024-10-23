package org.sopt.diary.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

    @Query(value="SELECT * FROM diary ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<DiaryEntity> findLastDiary();

}

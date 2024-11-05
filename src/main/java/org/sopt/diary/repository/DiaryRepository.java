package org.sopt.diary.repository;

import java.util.List;
import java.util.Optional;
import org.sopt.diary.repository.constant.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface DiaryRepository extends JpaRepository<DiaryEntity, Long> {

    @Query(value="SELECT * FROM diary ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<DiaryEntity> findLastDiary();

    List<DiaryEntity> findDiaryEntitiesByCategory(Category category);

    @Query(value = "SELECT * FROM diary WHERE user_id = :userId AND category = :category", nativeQuery = true)
    List<DiaryEntity> findDiaryEntitiesByUserIdAndCategory(Long userId, Category category);


    @Query(value = "SELECT * FROM diary WHERE user_id = :userId", nativeQuery = true)
    List<DiaryEntity> findAllByUser(Long userId);
}

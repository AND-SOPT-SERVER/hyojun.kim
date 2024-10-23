package org.sopt.diary.service;

import java.time.LocalDateTime;
import org.sopt.diary.repository.DiaryEntity;

public class Diary {
    private final long id;
    private final String content;
    private final LocalDateTime createdAt;

    public Diary(long id, String content, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Diary of(DiaryEntity diaryEntity) {
        return new Diary(diaryEntity.getId(), diaryEntity.getContent(), diaryEntity.getCreatedAt());
    }

    public static Diary from(DiaryEntity diaryEntity) {
        return of(diaryEntity);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

package org.sopt.diary.domain;

import java.time.LocalDateTime;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.constant.Category;

public class Diary {

    private final long id;
    private final String title;
    private final String content;
    private final User user;
    private final LocalDateTime createdAt;
    private final Category category;

    public Diary(long id, String title, String content, LocalDateTime createdAt,
        Category category, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.category = category;
        this.user = user;
    }



    public static Diary of(DiaryEntity diaryEntity) {
        return new Diary(diaryEntity.getId(), diaryEntity.getTitle(), diaryEntity.getContent(),
            diaryEntity.getCreatedAt(), diaryEntity.getCategory(), User.from(diaryEntity.getUserEntity()));
    }

    public static Diary from(DiaryEntity diaryEntity) {
        return of(diaryEntity);
    }

    public String getTitle() {
        return title;
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

    public int getContentLength(){
        return content.length();
    }

    public Category getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Diary{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", createdAt=" + createdAt +
            ", category=" + category +
            '}';
    }

    public static DiaryEntity toEntity(Diary diary) {
        return new DiaryEntity(diary.getTitle(), diary.getContent(), diary.getCreatedAt(), diary.getCategory());
    }
}

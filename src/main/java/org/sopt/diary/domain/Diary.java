package org.sopt.diary.domain;

import java.time.LocalDateTime;
import org.sopt.diary.repository.DiaryEntity;
import org.sopt.diary.repository.constant.Category;

public class Diary {

    private final long id;
    private final String title;
    private final String content;
    private final User user;
    private final LocalDateTime date;
    private final Category category;
    private final boolean isVisible;


    public Diary(long id, String title, String content, LocalDateTime date,
        Category category, User user, boolean isVisible) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.category = category;
        this.user = user;
        this.isVisible = isVisible;
    }



    public static Diary of(DiaryEntity diaryEntity) {

        return new Diary(diaryEntity.getId(), diaryEntity.getTitle(), diaryEntity.getContent(),
            diaryEntity.getDate(), diaryEntity.getCategory(), User.from(diaryEntity.getUserEntity()),
            diaryEntity.getVisible());
    }

    public static Diary from(DiaryEntity diaryEntity) {
        return of(diaryEntity);
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public boolean getIsVisible() {
        return isVisible;
    }


    public static DiaryEntity toEntity(Diary diary) {
        return new DiaryEntity(diary.getTitle(), diary.getContent(), diary.getCreatedAt(), diary.getCategory(), diary.getIsVisible());
    }
}

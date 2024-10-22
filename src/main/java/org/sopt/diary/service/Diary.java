package org.sopt.diary.service;

import org.sopt.diary.repository.DiaryEntity;

public class Diary {
    private final long id;
    private final String name;

    public Diary(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Diary of(DiaryEntity diaryEntity) {
        return new Diary(diaryEntity.getId(), diaryEntity.getName());
    }

    public static Diary from(DiaryEntity diaryEntity) {
        return of(diaryEntity);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

package org.sopt.diary.service;

public class Diary {
    private final long id;
    private final String name;

    public Diary(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

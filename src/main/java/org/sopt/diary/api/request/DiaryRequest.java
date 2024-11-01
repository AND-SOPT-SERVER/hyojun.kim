package org.sopt.diary.api.request;


public record DiaryRequest(String title, String content, String category) {

    public static DiaryRequest of(final String title, final String content, final String category) {
        return new DiaryRequest(title, content, category);
    }

}

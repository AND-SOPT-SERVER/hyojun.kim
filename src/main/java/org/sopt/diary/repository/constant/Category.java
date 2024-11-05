package org.sopt.diary.repository.constant;

public enum Category {
    FOOD,
    SCHOOL,
    MOVIE,
    EXERCISE;



    public static Category convertToCategory(final String category) {
        return valueOf(category.toUpperCase());
    }
}

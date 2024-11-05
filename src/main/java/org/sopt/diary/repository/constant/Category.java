package org.sopt.diary.repository.constant;

public enum Category {
    WORK,
    LIFE,
    HOBBY,
    ETC;



    public static Category convertToCategory(final String category) {
        if(category == null || category.isEmpty()) {
            return ETC;
        }
        return valueOf(category.toUpperCase());
    }
}

package org.sopt.diary.util;

public class LongParser {
    public static Long parse(final String value){
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}

package org.sopt.seminar1;

import org.sopt.seminar1.Main.UI.InvalidInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final int MAX_LENGTH = 30;
    private static final Pattern graphemePattern = Pattern.compile("\\X");
    private static final Matcher graphemeMatcher = graphemePattern.matcher("");


    public static void validateBody(final String body) {
        if (lengthCheck(body) > MAX_LENGTH)
            throw new InvalidInputException();
        if (body.trim().isBlank())
            throw new InvalidInputException();
    }

    private static int lengthCheck(String body) {
        if (body == null) {
            return 0;
        }
        graphemeMatcher.reset(body);
        int count = 0;
        while (graphemeMatcher.find()) {
            count++;
        }
        return count;
    }

}

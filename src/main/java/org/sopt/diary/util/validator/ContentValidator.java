package org.sopt.diary.util.validator;

import org.sopt.diary.exception.ContentLengthException;

@Validator
public class ContentValidator {

    private static final int MAX_CONTENT_LENGTH = 30;

    public void validate(final String content) {
        if(content.length() > MAX_CONTENT_LENGTH || content.isEmpty())
            throw new ContentLengthException();
    }

}

package org.sopt.diary.util.validator;

import java.time.LocalDateTime;
import java.util.Objects;
import org.sopt.diary.exception.InputTitleExcpetion;

@Validator
public class TitleValidator {

    private static final int MAX_TITLE_LENGTH = 10;


    public void validate(final String title) {
        if(Objects.isNull(title) || title.isEmpty())
            throw new InputTitleExcpetion();
        if(title.length()>MAX_TITLE_LENGTH)
            throw new InputTitleExcpetion();
    }

}

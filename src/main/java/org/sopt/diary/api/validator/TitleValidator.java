package org.sopt.diary.api.validator;

import java.util.Objects;
import org.sopt.diary.exception.InputTitleExcpetion;

@Validator
public class TitleValidator {

    public void validate(final String title) {
        if(Objects.isNull(title) || title.isEmpty())
            throw new InputTitleExcpetion();
    }

}

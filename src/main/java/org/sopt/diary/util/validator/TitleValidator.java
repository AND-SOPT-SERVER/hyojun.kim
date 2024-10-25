package org.sopt.diary.util.validator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.sopt.diary.exception.InputTitleExcpetion;

@Validator
public class TitleValidator {

    private final Set<String> titleSet  = new HashSet<>();


    public void validate(final String title) {
        if(Objects.isNull(title) || title.isEmpty())
            throw new InputTitleExcpetion();

        if(titleSet.contains(title))
            throw new InputTitleExcpetion("Title is already exist");

        titleSet.add(title);
    }

}

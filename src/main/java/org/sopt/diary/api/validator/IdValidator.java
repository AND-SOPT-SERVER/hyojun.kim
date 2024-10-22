package org.sopt.diary.api.validator;

import java.util.Objects;
import org.sopt.diary.exception.WrongIdException;

@Validator
public class IdValidator {
    public void validate(final Long id) {
        if (Objects.isNull(id) || id < 0) {
            throw new WrongIdException();
        }
    }

}

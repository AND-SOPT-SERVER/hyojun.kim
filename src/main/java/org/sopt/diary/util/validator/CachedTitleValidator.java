package org.sopt.diary.util.validator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.sopt.diary.exception.InputTitleExcpetion;

@Validator
public class CachedTitleValidator {

    private static final int CACHING_HOUR_TIME = 3;
    private final Set<String> titleSet  = new HashSet<>();
    private LocalDateTime expireTime;


    public void validate(final String title) {
        if(refreshCacheSet(expireTime)){
            expireTime = LocalDateTime.now().plusHours(CACHING_HOUR_TIME);
            titleSet.clear();
        }

        if(Objects.isNull(title) || title.isEmpty())
            throw new InputTitleExcpetion();

        if(titleSet.contains(title))
            throw new InputTitleExcpetion("Title is already exist");

        titleSet.add(title);
    }

    private boolean refreshCacheSet(LocalDateTime expireTime) {
        if(Objects.isNull(expireTime))
            return true;

        return !expireTime.isBefore(LocalDateTime.now());
    }

}

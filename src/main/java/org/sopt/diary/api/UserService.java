package org.sopt.diary.api;

import java.util.Optional;
import org.sopt.diary.api.request.UserRequest;
import org.sopt.diary.repository.UserEntity;

public interface UserService {

    void signUp(final UserRequest userRequest);

    void signIn(final UserRequest userRequest);

    UserEntity findUserByUsername(final String username);

    UserEntity findById(final Long userId);

    boolean isExistUser(Long userId);
}

package org.sopt.diary.api;

import java.util.Optional;
import org.sopt.diary.repository.UserEntity;

public interface UserService {

    void signUp(final UserRequest userRequest);

    void signIn(final UserRequest userRequest);

    Optional<UserEntity> findUserByUsername(final String username);

    boolean isExistUser(Long userId);
}

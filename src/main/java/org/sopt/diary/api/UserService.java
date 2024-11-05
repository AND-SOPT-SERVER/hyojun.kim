package org.sopt.diary.api;

import java.util.Optional;
import org.sopt.diary.api.request.LoginRequest;
import org.sopt.diary.api.request.SignInRequest;
import org.sopt.diary.repository.UserEntity;

public interface UserService {


    UserEntity findUserByUsername(final String username);

    UserEntity findById(final Long userId);

    boolean isExistUser(Long userId);

    void login(LoginRequest loginRequest);

    Long signIn(SignInRequest signInRequest);
}

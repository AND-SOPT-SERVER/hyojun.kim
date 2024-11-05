package org.sopt.diary.service;

import java.util.Optional;
import org.sopt.diary.api.UserService;
import org.sopt.diary.api.request.LoginRequest;
import org.sopt.diary.api.request.SignInRequest;
import org.sopt.diary.domain.User;
import org.sopt.diary.exception.NotFoundUserException;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundUserException(username));
    }

    @Override
    public UserEntity findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundUserException(userId));
    }

    @Override
    public boolean isExistUser(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    @Transactional
    public void login(LoginRequest loginRequest) {
         userRepository.findByUsername(loginRequest.username())
            .filter(userEntity -> userEntity.getPassword().equals(loginRequest.password()))
            .orElseThrow(() -> new NotFoundUserException(loginRequest.username()));
    }

    @Override
    @Transactional
    public Long signIn(SignInRequest signInRequest) {
        User user = User.of(signInRequest.username(), signInRequest.password(),
            signInRequest.userNickname());
        UserEntity entity = User.toEntity(user);
        userRepository.save(entity);

        return entity.getId();
    }
}

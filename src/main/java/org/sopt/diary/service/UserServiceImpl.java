package org.sopt.diary.service;

import java.util.Optional;
import org.sopt.diary.api.UserRequest;
import org.sopt.diary.api.UserService;
import org.sopt.diary.repository.UserEntity;
import org.sopt.diary.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signUp(UserRequest userRequest) {

    }

    @Override
    public void signIn(UserRequest userRequest) {

    }

    @Override
    public Optional<UserEntity> findUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public boolean isExistUser(Long userId) {
        return userRepository.existsById(userId);
    }
}

package org.sopt.diary.service;

import java.util.Optional;
import org.sopt.diary.api.request.UserRequest;
import org.sopt.diary.api.UserService;
import org.sopt.diary.exception.NotFoundUserException;
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
}

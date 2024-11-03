package org.sopt.diary.api;

import org.sopt.diary.api.request.LoginRequest;
import org.sopt.diary.api.request.SignInRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity<String> register(SignInRequest signInRequest) {
        Long id = userService.signIn(signInRequest);
        return ResponseEntity.ok("userIdentified Key was created : id " + id);
    }



    @PostMapping("/login")
    ResponseEntity<String> login(LoginRequest loginRequest) {
        userService.login(loginRequest);
        return ResponseEntity.ok("login");
    }

}

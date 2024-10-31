package org.sopt.diary.domain;


import java.util.List;
import org.sopt.diary.repository.UserEntity;


public class User {
    private String userName;

    private String password;

    private String nickname;

    public User(String userName, String password,
        String nickname) {
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
    }

    public static User from(UserEntity userEntity) {
        return new User(userEntity.getUsername(), userEntity.getPassword(),
            userEntity.getNickname());
    }

    public static UserEntity toEntity(User user){
        return new UserEntity(user.getUserName(), user.getPassword(),
            user.getNickname());

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    public String getNickname() {
        return nickname;
    }
}

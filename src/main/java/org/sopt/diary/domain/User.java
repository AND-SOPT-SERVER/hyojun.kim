package org.sopt.diary.domain;


import java.time.LocalDateTime;
import java.util.List;
import org.sopt.diary.repository.UserEntity;


public class User {
    private String userName;

    private String password;

    private List<Diary> diaryEntities;

    private String nickname;

    public User(String userName, String password, List<Diary> diaryEntities,
        String nickname) {
        this.userName = userName;
        this.password = password;
        this.diaryEntities = diaryEntities;
        this.nickname = nickname;
    }

    public static User from(UserEntity userEntity) {
        return new User(userEntity.getUserName(), userEntity.getPassword(), userEntity.getDiaryEntities().stream().map(Diary::of).toList(),
            userEntity.getNickname());
    }

    public static UserEntity toEntity(User user){
        return new UserEntity(user.getUserName(), user.getPassword(), user.getDiaryEntities().stream().map(Diary::toEntity).toList(),
            user.getNickname());

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<Diary> getDiaryEntities() {
        return diaryEntities;
    }

    public String getNickname() {
        return nickname;
    }
}

package org.sopt.diary.domain;


import java.time.LocalDateTime;
import java.util.List;
import org.sopt.diary.repository.UserEntity;


public class User {
    private String userName;

    private String password;

    private List<Diary> diaryEntities;

    private LocalDateTime createdAt;


    public User(String userName, String password, List<Diary> diaryEntities,
        LocalDateTime createdAt) {
        this.userName = userName;
        this.password = password;
        this.diaryEntities = diaryEntities;
        this.createdAt = createdAt;
    }

    public static User from(UserEntity userEntity) {
        return new User(userEntity.getUserName(), userEntity.getPassword(), userEntity.getDiaryEntities().stream().map(Diary::of).toList(),
            userEntity.getCreatedAt());
    }

    public static UserEntity toEntity(User user){
        return new UserEntity(user.getUserName(), user.getPassword(), user.getDiaryEntities().stream().map(Diary::toEntity).toList(),
            user.getCreatedAt());

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

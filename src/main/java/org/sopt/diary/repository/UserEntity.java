package org.sopt.diary.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {

    public UserEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;

    private String password;

    @OneToMany(mappedBy = "userEntity")
    private List<DiaryEntity> diaryEntities;

   private String nickname;

    public UserEntity(String userName, String password, List<DiaryEntity> list,
        String nickname) {
        this.userName = userName;
        this.password = password;
        this.diaryEntities = list;
        this.nickname = nickname;
    }


    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<DiaryEntity> getDiaryEntities() {
        return diaryEntities;
    }

    public String getNickname() {
        return nickname;
    }
}

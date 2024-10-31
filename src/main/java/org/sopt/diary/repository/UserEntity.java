package org.sopt.diary.repository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private String username;

    private String password;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE)
    private List<DiaryEntity> diaryEntities;

   private String nickname;

    public UserEntity(String username, String password,
        String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

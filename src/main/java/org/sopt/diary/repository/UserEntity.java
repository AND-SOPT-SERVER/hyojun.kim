package org.sopt.diary.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "sopt_member")
public class UserEntity {

    public UserEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    @OneToMany(mappedBy = "userEntity")
    private List<DiaryEntity> diaryEntities;

    @CreatedDate
    private LocalDateTime createdAt;

    public UserEntity(String userName, String password, List<DiaryEntity> list,
        LocalDateTime createdAt) {
        this.userName = userName;
        this.password = password;
        this.diaryEntities = list;
        this.createdAt = createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

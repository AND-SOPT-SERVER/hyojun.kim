package org.sopt.diary.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.sopt.diary.repository.constant.Category;

@Entity
@Table(name = "DIARY")
public class DiaryEntity {

    public DiaryEntity(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column
    private LocalDateTime createdAt;

    public DiaryEntity(String content, String title, LocalDateTime createdAt, Category category) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.category = category;
    }


    public static DiaryEntity of( final String title, final String content, final LocalDateTime createdAt, final Category category){
        return new DiaryEntity(content, title, createdAt, category);
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getContent() {
        return content;
    }

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void update(final String title, final String content){
        this.title = title;
        this.content = content;
    }

    public void update(final String title, final String content, final Category category){
        this.title = title;
        this.content = content;
        this.category = category;
    }



}

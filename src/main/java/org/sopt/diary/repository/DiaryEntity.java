package org.sopt.diary.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "DIARY")
public class DiaryEntity {

    public DiaryEntity(){
    }

    public DiaryEntity(final String content, final String title, final LocalDateTime createdAt){
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime createdAt;


    public static DiaryEntity of( final String title, final String content, final LocalDateTime createdAt){
        return new DiaryEntity(content, title, createdAt);
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


    public void update(final String title, final String content){
        this.title = title;
        this.content = content;
    }



}

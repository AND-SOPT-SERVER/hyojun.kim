package org.sopt.diary.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;
import org.sopt.diary.repository.constant.Category;

@Entity
@Table(name = "diary")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column
    private LocalDateTime date;

    @ColumnDefault("true")
    private Boolean isVisible = true;

    public DiaryEntity(String content, String title, LocalDateTime date, Category category, Boolean isVisible) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.category = category;
        this.isVisible = isVisible;
    }


    public static DiaryEntity of( final String title, final String content, final LocalDateTime createdAt, final Category category){
        return new DiaryEntity(content, title, createdAt, category, true);
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDate() {
        return date;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Boolean getVisible() {
        return isVisible;
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

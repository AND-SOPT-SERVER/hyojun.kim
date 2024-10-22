package org.sopt.diary.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DiaryEntity {

    public DiaryEntity(){
    }

    public DiaryEntity(final String name, final String title){
        this.title = title;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String title;

    @Column
    public String name;


    public long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public void update(final String name, final String title){
        this.name = name;
        this.title = title;
    }



}

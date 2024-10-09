package org.sopt.seminar1;

import java.time.LocalDate;

public class Diary {

    private final Long id;
    private String body;



    public Diary(Long id, String body) {
        this.id = id;
        this.body = body;
    }

    public Long getId() {
        return id;
    }


    public void setBody(String body) {
        this.body = body;
    }
    public String getBody() {
        return body;
    }
}

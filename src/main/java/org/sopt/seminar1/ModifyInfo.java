package org.sopt.seminar1;

import java.time.LocalDate;

public class ModifyInfo {
    private LocalDate date;
    private int modifyCount;

    public ModifyInfo(LocalDate date, int modifyCount) {
        this.date = date;
        this.modifyCount = modifyCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getModifyCount() {
        return modifyCount;
    }


    public void addModifyCount() {
        this.modifyCount = this.modifyCount + 1;
    }

    public void resetModifyCount(){
        this.modifyCount=0;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

}

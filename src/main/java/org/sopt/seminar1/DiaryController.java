package org.sopt.seminar1;


import java.util.List;

public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

    /**
     * 과제는 put delete 까지 진행!
     * @return
     */

    Status getStatus() {
        return status;
    }

    void boot() {
        this.status = Status.RUNNING;
    }

    void finish() {
        this.status = Status.FINISHED;
    }

    // APIS
    final List<Diary> getList() {
        return diaryService.findAll();
    }

    final void post(final String body) {
        try {
            diaryService.save(body);
        }catch (IllegalArgumentException e){
            this.status = Status.ERROR;
        }
    }

    final void delete(final String id) {
        diaryService.delete(Long.parseLong(id));
    }

    final void patch(final String id, final String body) {
        diaryService.patch(Long.parseLong(id), body);
    }

    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}

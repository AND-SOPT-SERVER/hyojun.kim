package org.sopt.seminar1;

import org.sopt.seminar1.Main.UI.InvalidInputException;

import java.util.List;

public class DiaryController {
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();

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
        Validator.validateBody(body);
        diaryService.save(body);
    }

    final void delete(final String id) {
        String trimmedInputId = id.trim();
        diaryService.delete(Parser.stringToLong(trimmedInputId));
    }

    final void patch(final String id, final String body) {
        Validator.validateBody(body);
        String trimmedInputId = id.trim();
        diaryService.patch(Parser.stringToLong(trimmedInputId), body);
    }

    final void restore() {
        diaryService.restore();
    }


    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}

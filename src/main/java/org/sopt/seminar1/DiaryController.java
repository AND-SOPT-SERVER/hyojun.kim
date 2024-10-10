package org.sopt.seminar1;

import org.sopt.seminar1.Main.UI.InvalidInputException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiaryController {
    private static final int MAX_LENGTH=30;
    private Status status = Status.READY;
    private final DiaryService diaryService = new DiaryService();
    private final Pattern graphemePattern = Pattern.compile("\\X");
    private final Matcher graphemeMatcher = graphemePattern.matcher("");

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
        validateBody(body);
        diaryService.save(body);
    }

    final void delete(final String id) {
        try {
            String trimmedInputId = id.trim();
            diaryService.delete(Long.parseLong(trimmedInputId));
        }catch (NumberFormatException e){
            throw new InvalidInputException();
        }
    }

    final void patch(final String id, final String body) {
        try {
            validateBody(body);
            String trimmedInputId = id.trim();
            diaryService.patch(Long.parseLong(trimmedInputId), body);
        }catch (NumberFormatException e){
            throw new InvalidInputException();
        }
    }

    final void restore(){
        diaryService.restore();
    }


    private void validateBody(String body) {
        if(body.length() > MAX_LENGTH)
            throw new InvalidInputException();
        if(body.trim().isBlank())
            throw new InvalidInputException();
        if(countGraphemes(body) > MAX_LENGTH)
            throw new InvalidInputException();
    }

    private int countGraphemes(String body) {
        if (body == null) {
            return 0;
        }
        graphemeMatcher.reset(body);
        int count = 0;
        while (graphemeMatcher.find()) {
            count++;
        }
        System.out.println("count는 : " + count);
        return count;
    }


    enum Status {
        READY,
        RUNNING,
        FINISHED,
        ERROR,
    }
}

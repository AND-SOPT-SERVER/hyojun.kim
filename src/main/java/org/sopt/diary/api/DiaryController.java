package org.sopt.diary.api;

import java.util.List;
import org.sopt.diary.api.validator.RequestValidator;
import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryServiceImpl diaryServiceImpl;
    private final RequestValidator requestValidator;

    public DiaryController(DiaryServiceImpl diaryServiceImpl, RequestValidator requestValidator) {
        this.diaryServiceImpl = diaryServiceImpl;
        this.requestValidator = requestValidator;
    }

    @PostMapping
    ResponseEntity<String> createDiary(DiaryRequest diaryRequest) {
        requestValidator.validate(diaryRequest);
        diaryServiceImpl.createDiary(diaryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success to create diary");
    }


    @GetMapping
    ResponseEntity<DiaryListResponse> getDiaryList() {
        List<DiaryResponse> findDiaryListBySortedId = diaryServiceImpl.findDiaryList();
        return ResponseEntity.ok(DiaryListResponse.of(findDiaryListBySortedId));
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<DiaryResponse> getDiary(@PathVariable final Long diaryId) {
        requestValidator.validate(diaryId);
        final Diary diary = diaryServiceImpl.findDiaryById(diaryId);
        return ResponseEntity.ok(new DiaryResponse(diary.getId(), diary.getName()));
    }


}

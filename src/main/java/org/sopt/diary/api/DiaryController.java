package org.sopt.diary.api;

import java.util.List;
import org.sopt.diary.repository.constant.Category;
import org.sopt.diary.util.validator.RequestValidator;
import org.sopt.diary.service.Diary;
import org.sopt.diary.service.DiaryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DiaryController {

    private final DiaryService diaryService;
    private final RequestValidator requestValidator;

    public DiaryController(DiaryServiceImpl diaryService, RequestValidator requestValidator) {
        this.diaryService = diaryService;
        this.requestValidator = requestValidator;
    }

    @PostMapping("/diary")
    ResponseEntity<String> createDiary(@RequestBody DiaryRequest diaryRequest) {
        requestValidator.validate(diaryRequest);
        diaryService.createDiary(diaryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success to create diary");
    }

    @GetMapping("/diaries")
    ResponseEntity<DiaryListResponse> getDiaryList() {
        List<SimpleDiaryResponse> findDiaryListBySortedId = diaryService.findDiaryList();
        return ResponseEntity.ok(DiaryListResponse.of(findDiaryListBySortedId));
    }


    @GetMapping("/diaries/{category}")
    ResponseEntity<DiaryListResponse> getDiaryListByCategory(@PathVariable final Category category) {
        List<SimpleDiaryResponse> findDiaryListBySortedId = diaryService.findDiaryListByCategory(category);
        return ResponseEntity.ok(DiaryListResponse.of(findDiaryListBySortedId));
    }



    @GetMapping("/diary/{diaryId}")
    public ResponseEntity<DetailDiaryResponse> getDiary(@PathVariable final Long diaryId) {
        requestValidator.validate(diaryId);
        final Diary diary = diaryService.findDiaryById(diaryId);
        return ResponseEntity.ok(DetailDiaryResponse.of(diary));
    }

    @PatchMapping("/diary/{diaryId}")
    public ResponseEntity<String> updateDiary(@PathVariable final Long diaryId, @RequestBody DiaryRequest diaryRequest) {
        requestValidator.validateUpdateRequest(diaryId, diaryRequest);
        diaryService.updateDiary(diaryId, diaryRequest);
        return ResponseEntity.ok("Success to update diary");
    }


    @DeleteMapping("/diary/{diaryId}")
    public ResponseEntity<String> deleteDiary(@PathVariable final Long diaryId) {
        requestValidator.validate(diaryId);
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.ok("Success to delete diary");
    }



}

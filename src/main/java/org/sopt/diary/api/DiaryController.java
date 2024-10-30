package org.sopt.diary.api;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import org.sopt.diary.domain.Diary;
import org.sopt.diary.exception.NotFoundUserException;
import org.sopt.diary.repository.constant.Category;
import org.sopt.diary.service.DiaryServiceImpl;
import org.sopt.diary.util.LongParser;
import org.sopt.diary.util.constant.Sort;
import org.sopt.diary.util.validator.RequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DiaryController {

    private final DiaryService diaryService;
    private final UserService userService;
    private final RequestValidator requestValidator;

    public DiaryController(DiaryServiceImpl diaryService, UserService userService,
        RequestValidator requestValidator) {
        this.diaryService = diaryService;
        this.userService = userService;
        this.requestValidator = requestValidator;
    }

    @PostMapping("/diary")
    ResponseEntity<String> createDiary(@RequestBody DiaryRequest diaryRequest) {
        requestValidator.validate(diaryRequest);
        diaryService.createDiary(diaryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success to create diary");
    }

    @GetMapping("/diaries")
    ResponseEntity<CommonDiaryListResponse> getDiaryList(@RequestParam final Sort sort) {

        List<CommonDiaryResponse> findDiaryListBySortedId = diaryService.findDiaryList(sort);

        return ResponseEntity.ok(CommonDiaryListResponse.of(findDiaryListBySortedId));
    }

    @GetMapping("/diaries/my")
    ResponseEntity<MyDiaryListResponse> getMyDiaryList(HttpServletRequest req,
        @RequestParam final Sort sort) {
        String userId = req.getHeader("userId");
        Long parsedUserId = LongParser.parse(userId);
        if (!userService.isExistUser(parsedUserId)) {
            throw new NotFoundUserException(parsedUserId);
        }
        List<MyDiaryResponse> myDiaryList = diaryService.findMyDiaryList(parsedUserId, sort);
        return ResponseEntity.ok(MyDiaryListResponse.of(myDiaryList));
    }


    @GetMapping("/diaries/{category}")
    ResponseEntity<DiaryListResponse> getDiaryListByCategory(
        @PathVariable final Category category, @RequestParam final Sort sort) {
        List<CommonDiaryResponse> findDiaryListBySortedId = diaryService.findDiaryListByCategory(
            category, sort);
        return ResponseEntity.ok(DiaryListResponse.of(findDiaryListBySortedId));
    }

    @GetMapping("/diaries/my/{category}")
    ResponseEntity<DiaryListResponse> getMyDiaryListByCategory(
        @PathVariable final Category category, @RequestParam final Sort sort) {
        List<CommonDiaryResponse> findDiaryListBySortedId = diaryService.findDiaryListByCategory(
            category, sort);
        return ResponseEntity.ok(DiaryListResponse.of(findDiaryListBySortedId));
    }


    @GetMapping("/diary/{diaryId}")
    public ResponseEntity<DetailDiaryResponse> getDiary(@PathVariable final Long diaryId) {
        requestValidator.validate(diaryId);
        final Diary diary = diaryService.findDiaryById(diaryId);
        return ResponseEntity.ok(DetailDiaryResponse.of(diary));
    }

    @PatchMapping("/diary/{diaryId}")
    public ResponseEntity<String> updateDiary(@PathVariable final Long diaryId,
        @RequestBody DiaryRequest diaryRequest) {
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

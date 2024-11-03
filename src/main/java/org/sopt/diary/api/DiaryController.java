package org.sopt.diary.api;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.sopt.diary.api.request.DiaryRequest;
import org.sopt.diary.api.response.CommonDiaryListResponse;
import org.sopt.diary.api.response.CommonDiaryResponse;
import org.sopt.diary.api.response.DetailDiaryResponse;
import org.sopt.diary.api.response.DiaryListResponse;
import org.sopt.diary.api.response.MyDiaryListResponse;
import org.sopt.diary.api.response.MyDiaryResponse;
import org.sopt.diary.domain.Diary;
import org.sopt.diary.domain.User;
import org.sopt.diary.exception.NotFoundUserException;
import org.sopt.diary.exception.WrongUserAccessException;
import org.sopt.diary.repository.UserEntity;
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

    private static final String USERID_HEADER = "userId";
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
    ResponseEntity<String> createDiary(@RequestBody DiaryRequest diaryRequest, HttpServletRequest req) {
        String userId = req.getHeader(USERID_HEADER);
        Long parsedUserId = LongParser.parse(userId);
        if(!userService.isExistUser(parsedUserId))
            throw new NotFoundUserException(parsedUserId);
        requestValidator.validate(diaryRequest);
        UserEntity userByUsername = userService.findById(parsedUserId);
        diaryService.createDiary(diaryRequest, userByUsername);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success to create diary");
    }

    @GetMapping("/diaries")
    ResponseEntity<CommonDiaryListResponse> getDiaryListByCategory(
        @RequestParam(required = false) final Category category, @RequestParam(required = false) final Sort sort) {
        List<CommonDiaryResponse> findDiaryListBySortedId = diaryService.findDiaryListByCategory(
            category, sort);
        return ResponseEntity.ok(CommonDiaryListResponse.of(findDiaryListBySortedId));
    }

    @GetMapping("/diaries/me")
    ResponseEntity<MyDiaryListResponse> getMyDiaryListByCategory(
        @RequestParam(required = false) final Category category, @RequestParam(required = false) final Sort sort
    , HttpServletRequest req) {
        String userId = req.getHeader(USERID_HEADER);
        Long parsedUserId = LongParser.parse(userId);
        if(!userService.isExistUser(parsedUserId))
            throw new NotFoundUserException(parsedUserId);

        List<MyDiaryResponse> findDiaryListBySortedId = diaryService.findMyDiaryListByCategory(parsedUserId,
            category, sort);
        return ResponseEntity.ok(MyDiaryListResponse.of(findDiaryListBySortedId));
    }


    @GetMapping("/diary/{diaryId}")
    public ResponseEntity<DetailDiaryResponse> getDiary(@PathVariable final Long diaryId) {
        requestValidator.validate(diaryId);
        final Diary diary = diaryService.findDiaryById(diaryId);
        return ResponseEntity.ok(DetailDiaryResponse.of(diary));
    }

    @PatchMapping("/diary/{diaryId}") // userId 관련해서 넣기
    public ResponseEntity<String> updateDiary(@PathVariable final Long diaryId,
        @RequestBody DiaryRequest diaryRequest, HttpServletRequest req) {
        String userId = req.getHeader(USERID_HEADER);
        Long parsedUserId = LongParser.parse(userId);
        if(!userService.isExistUser(parsedUserId))
            throw new NotFoundUserException(parsedUserId);
        requestValidator.validateUpdateRequest(diaryId, diaryRequest);
        Diary diaryById = diaryService.findDiaryById(diaryId);
        User byId = User.from(userService.findById(parsedUserId));
        if(!diaryById.getUser().getNickname().equals(byId.getNickname()))
            throw new WrongUserAccessException();
        diaryService.updateDiary(diaryId, diaryRequest);
        return ResponseEntity.ok("Success to update diary");
    }


    @DeleteMapping("/diary/{diaryId}") // userId 관련해서 넣기
    public ResponseEntity<String> deleteDiary(@PathVariable final Long diaryId, HttpServletRequest req) {
        String userId = req.getHeader(USERID_HEADER);
        Long parsedUserId = LongParser.parse(userId);
        if(!userService.isExistUser(parsedUserId))
            throw new NotFoundUserException(parsedUserId);
        requestValidator.validate(diaryId);
        Diary diaryById = diaryService.findDiaryById(diaryId);
        User byId = User.from(userService.findById(parsedUserId));
        if(!diaryById.getUser().getNickname().equals(byId.getNickname()))
            throw new WrongUserAccessException();
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.ok("Success to delete diary");
    }


}

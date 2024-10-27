package org.sopt.diary.util.validator;


import org.sopt.diary.api.DiaryRequest;

@Validator
public class RequestValidator{

    private final IdValidator idValidator;
    private final CachedTitleValidator cachedTitleValidator;
    private final ContentValidator contentValidator;

    public RequestValidator(IdValidator idValidator, CachedTitleValidator cachedTitleValidator,
        ContentValidator contentValidator) {
        this.idValidator = idValidator;
        this.cachedTitleValidator = cachedTitleValidator;
        this.contentValidator = contentValidator;
    }

    public void validate(final DiaryRequest request) {
        cachedTitleValidator.validate(request.title());
        contentValidator.validate(request.content()); // client 가 요청을 잘못 보낸 경우! 400 에러 뱉을 수 있도록 유도
    }

    public void validate(final Long id) {
        idValidator.validate(id);
    }

    public void validateUpdateRequest(final Long id, final DiaryRequest request) {
        idValidator.validate(id);
        cachedTitleValidator.validate(request.title());
        contentValidator.validate(request.content());
    }

}

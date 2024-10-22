package org.sopt.diary.api.validator;


import org.sopt.diary.api.ContentValidator;
import org.sopt.diary.api.DiaryRequest;

@Validator
public class RequestValidator{

    private final IdValidator idValidator;
    private final TitleValidator titleValidator;
    private final ContentValidator contentValidator;

    public RequestValidator(IdValidator idValidator, TitleValidator titleValidator,
        ContentValidator contentValidator) {
        this.idValidator = idValidator;
        this.titleValidator = titleValidator;
        this.contentValidator = contentValidator;
    }

    public void validate(final DiaryRequest request) {
        titleValidator.validate(request.title());
        contentValidator.validate(request.content()); // client 가 요청을 잘못 보낸 경우! 400 에러 뱉을 수 있도록 유도
    }

    public void validate(final Long id) {
        idValidator.validate(id);
    }

}

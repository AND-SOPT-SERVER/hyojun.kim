package org.sopt.diary.api;


@Validator
public class RequestValidator{

    private final TitleValidator titleValidator;
    private final ContentValidator contentValidator;

    public RequestValidator(TitleValidator titleValidator, ContentValidator contentValidator) {
        this.titleValidator = titleValidator;
        this.contentValidator = contentValidator;
    }

    public void validate(final DiaryRequest request) {
        titleValidator.validate(request.title());
        contentValidator.validate(request.content()); // client 가 요청을 잘못 보낸 경우! 400 에러 뱉을 수 있도록 유도
    }


}

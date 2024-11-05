package org.sopt.diary.api.request;


/**
 *
 * "username" : "khyojun",
 * 	"userNickname" : "qwer",
 * 	"password" : "1q2w3e4r!"
 */
public record SignInRequest(String username, String password, String userNickname) {

    public static SignInRequest of(final String username, final String password, final String nickName) {
        return new SignInRequest(username, password, nickName);
    }

}

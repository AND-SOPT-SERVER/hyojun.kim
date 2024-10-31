package org.sopt.diary.api.request;

public record UserRequest(String username, String password) {

    public static UserRequest of(final String username, final String password) {
        return new UserRequest(username, password);
    }
}

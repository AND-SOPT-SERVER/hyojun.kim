package org.sopt.diary.api.request;

public record LoginRequest(String username, String password) {

    public static LoginRequest of(final String username, final String password) {
        return new LoginRequest(username, password);
    }
}

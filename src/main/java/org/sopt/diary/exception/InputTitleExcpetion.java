package org.sopt.diary.exception;

public class InputTitleExcpetion  extends RuntimeException{
    public InputTitleExcpetion() {
        super("Input Title is not valid");
    }

    public InputTitleExcpetion(String msg) {
        super(msg);
    }
}

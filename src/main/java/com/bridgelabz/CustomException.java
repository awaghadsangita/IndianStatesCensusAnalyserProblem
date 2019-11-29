package com.bridgelabz;

public class CustomException extends Exception {
    enum ExceptionType{
        INCORRECT_COUNT,NO_SUCH_FILE
    }
    ExceptionType type;
    public CustomException(ExceptionType type) {

        this.type=type;
    }
}

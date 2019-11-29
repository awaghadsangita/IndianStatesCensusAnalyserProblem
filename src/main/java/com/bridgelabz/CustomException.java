package com.bridgelabz;

public class CustomException extends Exception {
    enum ExceptionType{
        NO_SUCH_FILE,INVALID_FILETYPE
    }
    ExceptionType type;
    public CustomException(ExceptionType type) {

        this.type=type;
    }
}

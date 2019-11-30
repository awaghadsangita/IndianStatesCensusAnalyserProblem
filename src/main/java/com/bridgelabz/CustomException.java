package com.bridgelabz;

public class CustomException extends Exception {
    enum ExceptionType{
        NO_SUCH_FILE,INVALID_FILETYPE,INVALID_DELIMITER,RUNTIME_EXCEPTION
    }
    ExceptionType type;
    public CustomException(ExceptionType type) {

        this.type=type;
    }
}

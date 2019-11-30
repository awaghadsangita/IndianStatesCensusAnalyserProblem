package com.bridgelabz;

public class CustomException extends Exception {
    enum ExceptionType{
        INCORRECT_NUMBEROF_RECORDS,NO_SUCH_FILE,INVALID_FILETYPE,INVALID_DELIMITER,RUNTIME_EXCEPTION,CSV_REQUIRED_FIELD_EMPTY_EXCEPTION
    }
    ExceptionType type;
    public CustomException(ExceptionType type) {

        this.type=type;
    }
}

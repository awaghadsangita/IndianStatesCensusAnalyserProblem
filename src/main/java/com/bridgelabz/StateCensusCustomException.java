package com.bridgelabz;

public class StateCensusCustomException extends Exception {
    enum ExceptionType{
        NO_SUCH_FILE,INVALID_FILETYPE,CSV_REQUIRED_FIELD_EMPTY_EXCEPTION,
        INPUTOUTPUT_ISSUES,NO_SUCH_METHOD,IILEGAL_ACCESS_ISSUE,TARGET_INVOCATION_ISSUE
    }
    ExceptionType type;
    public StateCensusCustomException(ExceptionType type) {

        this.type=type;
    }
}

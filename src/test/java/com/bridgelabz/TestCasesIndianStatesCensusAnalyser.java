package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

public class TestCasesIndianStatesCensusAnalyser {
    @Test
    public void GivenStateCSV_NumberOfRecord_ShouldMatchExpected() {
        StateCensusAnalyser Obj = new StateCensusAnalyser();
        try {
            String stateCountResult = Obj.matchStateCount(37);
            Assert.assertEquals("HAPPY", stateCountResult);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GivneStateCSV_IncorrectFile_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            String s = obj.matchStateCount(37);
        }catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void GivenStateCSV_InCorrectFileType_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCount(37);
        } catch (CustomException e) {
             Assert.assertEquals(CustomException.ExceptionType.INVALID_FILETYPE,e.type);
        }
    }

    @Test
    public void GivenStateCSV_InCorrectDelimiter_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCount(37);
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION,e.type);
        }
    }

    @Test
    public void GivenStateCSV_InCorrectHeader_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCount(37);
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION,e.type);
        }
    }
    //Following Test cases for stateCensusData
    @Test
    public void GivenStateCensusCSV_NumberOfRecord_ShouldMatchExpected() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            String result= obj.matchStateCensusCount(29);
            Assert.assertEquals("HAPPY",result);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GivenStateCensusCSV_InCorrectFile_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCensusCount(29);
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void GivenStateCensusCSV_DelimiterInCorrect_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCensusCount(29);
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION,e.type);
        }
    }
    @Test
    public void GivenStateCensusCSV_InCorrectHeaders_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCensusCount(29);
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION,e.type);
        }
    }
}

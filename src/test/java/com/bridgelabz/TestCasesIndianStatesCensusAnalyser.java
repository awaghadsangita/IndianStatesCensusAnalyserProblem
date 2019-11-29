package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestCasesIndianStatesCensusAnalyser {
    @Test
    public void GivenStateCSV_NumberOfRecord_ShouldMatchExpected() {
        StateCensusAnalyser Obj = new StateCensusAnalyser();
        try {
            String stateCountResult = Obj.matchStateCount(29);
            Assert.assertEquals("HAPPY", stateCountResult);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GivneStateCSV_IncorrectNumberOfRecords_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            String s = obj.matchStateCount(29);
        }catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.INCORRECT_COUNT,e.type);
        }
    }
}

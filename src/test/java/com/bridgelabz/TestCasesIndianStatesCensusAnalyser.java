package com.bridgelabz;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestCasesIndianStatesCensusAnalyser {
    private static final String STRING_ARRAY_SAMPLE = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCodeCensusData.csv";
    private static final String STATE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCode.csv";
    private static final String STATE_CENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData.csv";
    private static  final String STATE_CLASSNAME="com.bridgelabz.CSVStates";
    private static  final String STATE_CENSUS_CLASSNAME="com.bridgelabz.CSVStateCensus";
    @Test
    public void GivenStateCSV_NumberOfRecord_ShouldMatchExpected() {
        StateCensusAnalyser Obj = new StateCensusAnalyser();
        try {
            String stateCountResult = Obj.matchStateCount(37,STATE_CSV_FILE_PATH,STATE_CLASSNAME,',');
            Assert.assertEquals("HAPPY", stateCountResult);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GivneStateCSV_IncorrectFile_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            String s = obj.matchStateCount(37,"/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCode1.csv","STATE_CLASSNAME",',');
        }catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void GivenStateCSV_InCorrectFileType_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCount(37,"/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCode.json",STATE_CLASSNAME,',');
        } catch (CustomException e) {
             Assert.assertEquals(CustomException.ExceptionType.INVALID_FILETYPE,e.type);
        }
    }

    @Test
    public void GivenStateCSV_InCorrectDelimiter_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCount(37,STATE_CSV_FILE_PATH,STATE_CLASSNAME,';');
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION,e.type);
        }
    }

    @Test
    public void GivenStateCSV_InCorrectHeader_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCount(37,STATE_CSV_FILE_PATH,STATE_CLASSNAME,',');
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION,e.type);
        }
    }
    //Following Test cases for stateCensusData
    @Test
    public void GivenStateCensusCSV_NumberOfRecord_ShouldMatchExpected() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            String result= obj.matchStateCensusCount(29,STATE_CENSUS_CSV_FILE_PATH,STATE_CENSUS_CLASSNAME,',');
            Assert.assertEquals("HAPPY",result);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GivenStateCensusCSV_InCorrectFile_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCensusCount(29,"/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData1.csv",STATE_CENSUS_CLASSNAME,',');
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }
    @Test
    public void GivenStateCensusCSV_InCorrectFileType_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCensusCount(29,"/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData1.json",STATE_CENSUS_CLASSNAME,',');
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.INVALID_FILETYPE,e.type);
        }
    }
    @Test
    public void GivenStateCensusCSV_DelimiterInCorrect_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCensusCount(29,STATE_CENSUS_CSV_FILE_PATH,STATE_CENSUS_CLASSNAME,'\t');
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION,e.type);
        }
    }
    @Test
    public void GivenStateCensusCSV_InCorrectHeaders_ShouldThrowCustomException() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            obj.matchStateCensusCount(29,STATE_CENSUS_CSV_FILE_PATH,STATE_CENSUS_CLASSNAME,',');
        } catch (CustomException e) {
            Assert.assertEquals(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION,e.type);
        }
    }

    @Test
    public void testcase() {
        StateCensusAnalyser obj=new StateCensusAnalyser();
        try {
            String result=obj.matchCensusObjectWithStateCode(28,STATE_CSV_FILE_PATH, STATE_CLASSNAME, STATE_CENSUS_CSV_FILE_PATH, STATE_CENSUS_CLASSNAME,STRING_ARRAY_SAMPLE, ',');
            Assert.assertEquals("HAPPY",result);

        } catch (CustomException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }


}

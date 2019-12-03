package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestCasesIndianStatesCensusAnalyser {
    private static final String STATE_CENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData.csv";
    private static  final String STATE_CENSUS_CLASSNAME="com.bridgelabz.CSVStateCensus";

    @Test
    public void GivenStateCensusCSV_NumberOfRecord_ShouldMatchExpected() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> result= obj.matchStateCensusCount(29,STATE_CENSUS_CSV_FILE_PATH,STATE_CENSUS_CLASSNAME,',');
            Assert.assertEquals(29,result.size());
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
    public void GivenStateCensusJSON_ShouldReturnMostPopulousState() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> result= obj.matchStateCensusCount(29,STATE_CENSUS_CSV_FILE_PATH,STATE_CENSUS_CLASSNAME,',');
            Assert.assertEquals("Uttar Pradesh",result.get(0).getState());
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GivenStateCensusJSON_ShouldReturnLeastPopulousState() {
        StateCensusAnalyser obj = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> result= obj.matchStateCensusCount(29,STATE_CENSUS_CSV_FILE_PATH,STATE_CENSUS_CLASSNAME,',');
            Assert.assertEquals("Sikkim",result.get(result.size()-1).getState());
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
}

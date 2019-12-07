package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestCasesIndianStatesCensusAnalyser {
    private static final String STATE_CENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData.csv";

    @Test
    public void givenStateCensusCSV_NumberOfRecord_ShouldMatchExpected() {
        StateCensusAnalyser<String> censusAnalyser = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> result = censusAnalyser.giveStateCensusList(STATE_CENSUS_CSV_FILE_PATH, ',');
            Assert.assertEquals(29, result.size());
        } catch (StateCensusCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusCSV_InCorrectFile_ShouldThrowCustomException() {
        StateCensusAnalyser<String> censusAnalyser = new StateCensusAnalyser<>();
        try {
            censusAnalyser.giveStateCensusList("/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData1.csv", ',');
        } catch (StateCensusCustomException e) {
            Assert.assertEquals(StateCensusCustomException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenStateCensusCSV_InCorrectFileType_ShouldThrowCustomException() {
        StateCensusAnalyser<String> censusAnalyser = new StateCensusAnalyser<>();
        try {
            censusAnalyser.giveStateCensusList("/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData1.json", ',');
        } catch (StateCensusCustomException e) {
            Assert.assertEquals(StateCensusCustomException.ExceptionType.INVALID_FILETYPE, e.type);
        }
    }

    @Test
    public void givenStateCensusCSV_DelimiterInCorrect_ShouldThrowCustomException() {
        StateCensusAnalyser<String> censusAnalyser = new StateCensusAnalyser<>();
        try {
            censusAnalyser.giveStateCensusList(STATE_CENSUS_CSV_FILE_PATH, ';');
        } catch (StateCensusCustomException e) {
            Assert.assertEquals(StateCensusCustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenStateCensusCSV_InCorrectHeaders_ShouldThrowCustomException() {
        StateCensusAnalyser<String> censusAnalyser = new StateCensusAnalyser<>();
        try {
            censusAnalyser.giveStateCensusList(STATE_CENSUS_CSV_FILE_PATH, ',');
        } catch (StateCensusCustomException e) {
            Assert.assertEquals(StateCensusCustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION, e.type);
        }
    }

    @Test
    public void givenStateCensusJSON_ShouldReturnMostPopulousState() {
        StateCensusAnalyser<Integer> censusAnalyser = new <String>StateCensusAnalyser();
        try {
            List<CSVStateCensus> result = censusAnalyser.findSmallestAndLargest(STATE_CENSUS_CSV_FILE_PATH, "population", ',');
            Assert.assertEquals("Uttar Pradesh", result.get(0).getState());
        } catch (StateCensusCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusJSON_ShouldReturnLeastPopuloustate() {
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> result = censusAnalyser.findSmallestAndLargest(STATE_CENSUS_CSV_FILE_PATH, "population", ',');
            Assert.assertEquals("Sikkim", result.get(result.size() - 1).getState());
        } catch (StateCensusCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusJSON_ShouldReturnMostPopulationDensityState() {
        StateCensusAnalyser<Integer> censusAnalyser = new <String>StateCensusAnalyser();
        try {
            List<CSVStateCensus> result = censusAnalyser.findSmallestAndLargest(STATE_CENSUS_CSV_FILE_PATH, "density", ',');
            Assert.assertEquals("Bihar",result.get(0).getState());
        } catch (StateCensusCustomException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenStateCensusJSON_ShouldReturnLeastPopulationDensityState() {
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> result = censusAnalyser.findSmallestAndLargest(STATE_CENSUS_CSV_FILE_PATH, "density", ',');
            Assert.assertEquals("Arunachal Pradesh", result.get(result.size() - 1).getState());
        } catch (StateCensusCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCensusJSON_ShouldReturnLargestAreaState() {
        StateCensusAnalyser<Integer> censusAnalyser = new <String>StateCensusAnalyser();
        try {
            List<CSVStateCensus> result = censusAnalyser.findSmallestAndLargest(STATE_CENSUS_CSV_FILE_PATH, "area", ',');
            Assert.assertEquals("Rajasthan",result.get(0).getState());
        } catch (StateCensusCustomException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenStateCensusJSON_ShouldReturnSmallestAreaState() {
        StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        try {
            List<CSVStateCensus> result = censusAnalyser.findSmallestAndLargest(STATE_CENSUS_CSV_FILE_PATH, "area", ',');
            Assert.assertEquals("Arunachal Pradesh", result.get(result.size() - 1).getState());
        } catch (StateCensusCustomException e) {
            e.printStackTrace();
        }
    }
}

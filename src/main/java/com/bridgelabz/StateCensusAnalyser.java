package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    private static final String STATE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCode.csv";
    private static final String STATE_CENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData.csv";


    public String matchStateCount(int cnt) throws CustomException {
        int count = 0;
        try {

            if(!STATE_CSV_FILE_PATH.contains(".csv"))
            {
                throw new CustomException(CustomException.ExceptionType.INVALID_FILETYPE);
            }
            Reader reader = Files.newBufferedReader(Paths.get(STATE_CSV_FILE_PATH));
            CsvToBean<CSVStates> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStates.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVStates> csvDataIterator = csvToBean.iterator();
             while (csvDataIterator.hasNext()) {
                ++count;
                CSVStates csvState =csvDataIterator.next();
                System.out.println("SrNo : " + csvState.getSrNo());
                System.out.println("StateName : " + csvState.getStateName());
                System.out.println("TIN : " + csvState.getTIN());
                System.out.println("StateCode : " + csvState.getStateCode());
                System.out.println("==========================");
            }
            if (count == cnt) {
                return "HAPPY";
            }
            else{
                throw new CustomException(CustomException.ExceptionType.INCORRECT_NUMBEROF_RECORDS);
            }
        } catch (FileNotFoundException e) {
            throw new CustomException(CustomException.ExceptionType.NO_SUCH_FILE);
        }
        catch (RuntimeException e){
            throw new CustomException(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION);
        }
        catch (IOException e) {

        }
        return null;
    }


}

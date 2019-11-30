package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StateCensusAnalyser {
    private static final String STATE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCode.json";
    private static final String STATE_CENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData.csv";

    public String matchStateCount(int cnt) throws CustomException {
        int count = 0;
        try {
            String extension = getFileExtension(new File(STATE_CSV_FILE_PATH));
            if(extension!="csv")
            {
                throw new CustomException(CustomException.ExceptionType.INVALID_FILETYPE);
            }
            Reader reader = Files.newBufferedReader(Paths.get(STATE_CSV_FILE_PATH));
            CsvToBean<STATEData> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(STATEData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<STATEData> csvDataIterator = csvToBean.iterator();

            while (csvDataIterator.hasNext()) {
                ++count;
                STATEData csvState =csvDataIterator.next();
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
                throw new CustomException(CustomException.ExceptionType.NO_SUCH_FILE);
            }
        } catch (FileNotFoundException e) {
            throw new CustomException(CustomException.ExceptionType.NO_SUCH_FILE);
        }
//        catch (RuntimeException e){
//            throw new CustomException(CustomException.ExceptionType.RUNTIME_EXCEPTION);
//        }
        catch (IOException e) {

        }
        return null;
    }
    private static String getFileExtension(File file) {
        String extension = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }

}

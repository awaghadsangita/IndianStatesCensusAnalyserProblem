package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {



    public String matchStateCount(int cnt,String filePath,String className,char separator) throws CustomException {
        int count = 0;
        try {

            if (!filePath.contains(".csv")) {
                throw new CustomException(CustomException.ExceptionType.INVALID_FILETYPE);
            }

            CsvToBean<CSVStates> csvToBean = this.OpenCSVBuilder(filePath,className,separator);

            Iterator<CSVStates> csvDataIterator = csvToBean.iterator();
            while (csvDataIterator.hasNext()) {
                ++count;
                CSVStates csvState = csvDataIterator.next();
                System.out.println("SrNo : " + csvState.getSrNo());
                System.out.println("StateName : " + csvState.getStateName());
                System.out.println("TIN : " + csvState.getTIN());
                System.out.println("StateCode : " + csvState.getStateCode());
                System.out.println("==========================");
            }
            if (count == cnt) {
                return "HAPPY";
            }
        }catch (RuntimeException e){
            throw new CustomException(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION);
        }

        return null;
    }

    public String matchStateCensusCount(int cnt,String filePath,String className,char separator) throws CustomException {
        int count = 0;
        try{
            if(!filePath.contains(".csv"))
            {
                throw new CustomException(CustomException.ExceptionType.INVALID_FILETYPE);
            }

            CsvToBean<CSVStateCensus> csvToBean=this.OpenCSVBuilder(filePath,className,separator);

            Iterator<CSVStateCensus> csvDataIterator = csvToBean.iterator();
            while (csvDataIterator.hasNext()) {
                ++count;
                CSVStateCensus csvStateCensus =csvDataIterator.next();
                System.out.println("State : " + csvStateCensus.getState());
                System.out.println("Population : " + csvStateCensus.getPopulation());
                System.out.println("AreaInSqKm : " + csvStateCensus.getAreaInSqKm());
                System.out.println("DensityPerSqKm : " + csvStateCensus.getDensityPerSqKm());
                System.out.println("==========================");
            }
            if (count == cnt) {
                return "HAPPY";
            }

        }catch (RuntimeException e){
            throw new CustomException(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION);
        }
        return null;
    }
    public <T>CsvToBean OpenCSVBuilder(String filename,String classname,char separator) throws CustomException {

        CsvToBean<T> csvToBean;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Class.forName(classname))
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(separator)
                    .build();

            return csvToBean;
        }catch (NoSuchFileException e) {
            throw new CustomException(CustomException.ExceptionType.NO_SUCH_FILE);
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

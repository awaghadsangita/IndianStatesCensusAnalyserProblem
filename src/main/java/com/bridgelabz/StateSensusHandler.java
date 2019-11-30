package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateSensusHandler {
    private static final String STATE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCode.csv";
    private static final String STATE_CENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData.json";


    public String matchStateCensusCount(int cnt) throws CustomException {
        int count = 0;
        try {
            if(!STATE_CENSUS_CSV_FILE_PATH.contains(".csv"))
            {
                throw new CustomException(CustomException.ExceptionType.INVALID_FILETYPE);
            }
            Reader reader = Files.newBufferedReader(Paths.get(STATE_CENSUS_CSV_FILE_PATH));
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

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
            }else
            {
                throw new CustomException(CustomException.ExceptionType.INCORRECT_NUMBEROF_RECORDS);
            }
        } catch(FileNotFoundException e){
            throw new CustomException(CustomException.ExceptionType.NO_SUCH_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (RuntimeException e){
            throw new CustomException(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION);
        }


        return null;
    }
}

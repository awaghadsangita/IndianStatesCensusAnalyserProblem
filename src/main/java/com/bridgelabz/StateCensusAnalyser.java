package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    private static final String SAMPLE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData1.csv";

    public String matchStateCount(int cnt) throws CustomException {
        int count = 0;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CsvToBean<CENSUSData> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CENSUSData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CENSUSData> csvDataIterator = csvToBean.iterator();

            while (csvDataIterator.hasNext()) {
                ++count;
                csvDataIterator.next();
            }
            if (count == cnt) {
                return "HAPPY";
            }
        } catch (FileNotFoundException e) {
            throw new CustomException(CustomException.ExceptionType.NO_SUCH_FILE);
        } catch (IOException e) {

        }
        return null;
    }
}

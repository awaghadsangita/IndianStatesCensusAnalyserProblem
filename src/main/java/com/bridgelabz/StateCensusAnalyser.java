package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    private static final String SAMPLE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensusData.csv";

    public String matchStateCount(int cnt) throws IOException {
        int count = 0;

        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        CsvToBean<CENSUSData> csvToBean = new CsvToBeanBuilder(reader)
                .withType(CENSUSData.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        Iterator<CENSUSData> csvDataIterator = csvToBean.iterator();

        while (csvDataIterator.hasNext()) {
            count++;
            csvDataIterator.next();
        }
        String result="none";
        if(count==cnt)
        {
            return "HAPPY";
        }else
        {
            return "SAD";
        }

    }


}

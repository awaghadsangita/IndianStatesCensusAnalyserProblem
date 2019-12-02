package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class OpenCSVBuilderClass {
    public <T>CsvToBean OpenCSVBuilder(String filename,String classname) throws CustomException {

        CsvToBean<T> csvToBean;
        try {

            Reader reader = Files.newBufferedReader(Paths.get(filename));
            csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Class.forName(classname))
                    .withIgnoreLeadingWhiteSpace(true)
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

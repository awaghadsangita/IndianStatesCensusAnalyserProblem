package com.bridgelabz;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;


public class StateCensusAnalyser<T extends Comparable<T>> {
    public List<CSVStateCensus> giveStateCensusList(String filePath, char separator) throws StateCensusCustomException {
        List<CSVStateCensus> csvCensusList = null;
        try {
            if (!filePath.contains(".csv")) {
                throw new StateCensusCustomException(StateCensusCustomException.ExceptionType.INVALID_FILETYPE);
            }
            Reader reader = Files.newBufferedReader(Paths.get(filePath));

            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCensus.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(separator)
                    .build();
            csvCensusList = csvToBean.parse();
        } catch (NoSuchFileException e) {
            throw new StateCensusCustomException(StateCensusCustomException.ExceptionType.NO_SUCH_FILE);
        } catch (IOException e) {
            throw new StateCensusCustomException(StateCensusCustomException.ExceptionType.INPUTOUTPUT_ISSUES);
        } catch (RuntimeException e) {
            throw new StateCensusCustomException(StateCensusCustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION);
        }

        return csvCensusList;
    }

    public List<CSVStateCensus> findSmallestAndLargest(String filePath, String methodName, char separator) throws StateCensusCustomException {
        List<CSVStateCensus> censusList = this.giveStateCensusList(filePath, separator);
        this.sort(censusList, methodName);

        return censusList;
    }

    public void sort(List<CSVStateCensus> csvCensusList, String methodName) throws StateCensusCustomException {
        try {
            for (int i = 0; i < csvCensusList.size() - 1; i++) {
                for (int j = 0; j < csvCensusList.size() - i - 1; j++) {
                    Class currentClass = csvCensusList.get(j).getClass();
                    Method currentMethod = currentClass.getDeclaredMethod(methodName);
                    T value1 = (T) currentMethod.invoke(csvCensusList.get(j));
                    Class nextClass = csvCensusList.get(j + 1).getClass();
                    Method nextMethod = nextClass.getDeclaredMethod(methodName);
                    T value2 = (T) nextMethod.invoke(csvCensusList.get(j + 1));
                    if (value1.compareTo(value2) < 0) {
                        CSVStateCensus tempObj = csvCensusList.get(j);
                        csvCensusList.set(j, csvCensusList.get(j + 1));
                        csvCensusList.set(j + 1, tempObj);
                    }
                }
            }
            this.writeToJsonFile(csvCensusList);
        } catch (NoSuchMethodException e) {
            throw new StateCensusCustomException(StateCensusCustomException.ExceptionType.NO_SUCH_METHOD);
        } catch (IllegalAccessException e) {
            throw new StateCensusCustomException(StateCensusCustomException.ExceptionType.IILEGAL_ACCESS_ISSUE);
        } catch (InvocationTargetException e) {
            throw new StateCensusCustomException(StateCensusCustomException.ExceptionType.TARGET_INVOCATION_ISSUE);
        }
    }

    public void writeToJsonFile(List<CSVStateCensus> list) {
        String filename = "/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensus.json";
        Gson gson = new Gson();
        String json = gson.toJson(list);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

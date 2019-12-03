package com.bridgelabz;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StateCensusAnalyser {
    public String matchStateCount(int cnt, String filePath, String className, char separator) throws CustomException {
        int count = 0;
        try {

            if (!filePath.contains(".csv")) {
                throw new CustomException(CustomException.ExceptionType.INVALID_FILETYPE);
            }

            CsvToBean<CSVStates> csvToBean = this.OpenCSVBuilder(filePath, className, separator);
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
        } catch (RuntimeException e) {
            throw new CustomException(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION);
        }

        return null;
    }

    public String matchStateCensusCount(int cnt, String filePath, String className, char separator) throws CustomException {
        int count = 0;
        try {
            if (!filePath.contains(".csv")) {
                throw new CustomException(CustomException.ExceptionType.INVALID_FILETYPE);
            }

            CsvToBean<CSVStateCensus> csvToBean = this.OpenCSVBuilder(filePath, className, separator);
            Iterator<CSVStateCensus> csvDataIterator = csvToBean.iterator();

            while (csvDataIterator.hasNext()) {
                ++count;
                CSVStateCensus csvStateCensus = csvDataIterator.next();
                System.out.println("State : " + csvStateCensus.getState());
                System.out.println("Population : " + csvStateCensus.getPopulation());
                System.out.println("AreaInSqKm : " + csvStateCensus.getAreaInSqKm());
                System.out.println("DensityPerSqKm : " + csvStateCensus.getDensityPerSqKm());
                System.out.println("==========================");
            }
            if (count == cnt) {
                return "HAPPY";
            }

        } catch (RuntimeException e) {
            throw new CustomException(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION);
        }
        return null;
    }

    public <T> CsvToBean OpenCSVBuilder(String filename, String classname, char separator) throws CustomException {

        CsvToBean<T> csvToBean;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Class.forName(classname))
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(separator)
                    .build();

            return csvToBean;
        } catch (NoSuchFileException e) {
            throw new CustomException(CustomException.ExceptionType.NO_SUCH_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String matchCensusObjectWithStateCode(int cnt,String stateCodeFileName, String stateCodeClassName, String stateCensusFileName, String stateCensusClassName,String newFileName ,char separator) throws CustomException, CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        CsvToBean<CSVStateCensus> csvStateCensusToBean = this.OpenCSVBuilder(stateCensusFileName, stateCensusClassName, separator);
        Iterator<CSVStateCensus> censusIterator = csvStateCensusToBean.iterator();
        List<StateCensusData> stateCensusDataObjectList = new ArrayList<>();

        while (censusIterator.hasNext()) {
            CsvToBean<CSVStates> csvStateCodeToBean = this.OpenCSVBuilder(stateCodeFileName, stateCodeClassName, separator);
            Iterator<CSVStates> csvStateCodeDataIterator = csvStateCodeToBean.iterator();
            CSVStateCensus csvCensus = censusIterator.next();
            while (csvStateCodeDataIterator.hasNext()) {
                CSVStates csvStateCode = csvStateCodeDataIterator.next();
                if (csvCensus.getState().equals(csvStateCode.getStateName())) {
                    StateCensusData obj = new StateCensusData();
                    obj.setSrNo(csvStateCode.getSrNo());
                    obj.setStateName(csvStateCode.getStateName());
                    obj.setStateCode(csvStateCode.getStateCode());
                    obj.setTIN(csvStateCode.getTIN());
                    obj.setPopulation(csvCensus.getPopulation());
                    obj.setAreaInSqKm(csvCensus.getAreaInSqKm());
                    obj.setDensityPerSqKm(csvCensus.getDensityPerSqKm());
                    stateCensusDataObjectList.add(obj);
                    break;
                }
            }
        }
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(newFileName));
        ) {
            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            String[] headerRecord = {"SrNo", "StateName", "StateCode", "TIN", "Population", "AreaInSqKm", "DensityPerSqKm"};
            csvWriter.writeNext(headerRecord);
            for (int i = 0; i < stateCensusDataObjectList.size(); i++) {
                csvWriter.writeNext(new String[]{stateCensusDataObjectList.get(i).getSrNo(), stateCensusDataObjectList.get(i).getStateName(), stateCensusDataObjectList.get(i).getStateCode(), String.valueOf(stateCensusDataObjectList.get(i).getTIN()), String.valueOf(stateCensusDataObjectList.get(i).getPopulation()), String.valueOf(stateCensusDataObjectList.get(i).getAreaInSqKm()), String.valueOf(stateCensusDataObjectList.get(i).getDensityPerSqKm())});
            }
        }
        if(cnt==stateCensusDataObjectList.size())
        return "HAPPY";

        return null;
    }


}

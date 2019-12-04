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


public class StateCensusAnalyser <T extends Comparable<T>>{
    public List<CSVStateCensus> matchStateCensusCount (int cnt, String filePath, String className, char separator,String methodname) throws CustomException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        List<CSVStateCensus> csvCensusList = null;
        try {
            if (!filePath.contains(".csv")) {
                throw new CustomException(CustomException.ExceptionType.INVALID_FILETYPE);
            }
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Class.forName(className))
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(separator)
                    .build();
            csvCensusList = csvToBean.parse();
        }catch (NoSuchFileException e) {
            throw new CustomException(CustomException.ExceptionType.NO_SUCH_FILE);
        }catch (RuntimeException | IOException | ClassNotFoundException e) {
            throw new CustomException(CustomException.ExceptionType.CSV_REQUIRED_FIELD_EMPTY_EXCEPTION);
        }

        this.Sort(csvCensusList,methodname);

        return csvCensusList;
    }

    public void Sort(List<CSVStateCensus> csvCensusList,String methodname) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for(int i=0;i<csvCensusList.size()-1;i++){
            for(int j=0;j<csvCensusList.size()-i-1;j++){
                Class cls = csvCensusList.get(j).getClass();
                Method methodcall = cls.getDeclaredMethod(methodname);
                T value1=(T)methodcall.invoke(csvCensusList.get(j));
                Class cls1 = csvCensusList.get(j+1).getClass();
                Method methodcall1 = cls1.getDeclaredMethod(methodname);
                T value2=(T)methodcall1.invoke(csvCensusList.get(j+1));
                if(value1.compareTo(value2)<0){
                    CSVStateCensus tempObj=csvCensusList.get(j);
                    csvCensusList.set(j,csvCensusList.get(j+1));
                    csvCensusList.set(j+1,tempObj);
                }
            }
        }

    }

    public void writeToJsonFile(List<CSVStateCensus> list){
        String filename="/home/admin1/IdeaProjects/IndianStatesCensusAnalyserProblem/src/main/resources/StateCensus.json";
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

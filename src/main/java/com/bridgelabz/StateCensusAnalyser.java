package com.bridgelabz;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;


public class StateCensusAnalyser {
    public List<CSVStateCensus> matchStateCensusCount(int cnt, String filePath, String className, char separator) throws CustomException {

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

        SortState(csvCensusList);
        this.SortStateByPopulation(csvCensusList);
        return csvCensusList;
    }

    public void SortState(List<CSVStateCensus> csvCensusList) {
        for(int i=0;i<csvCensusList.size()-1;i++){
            for(int j=0;j<csvCensusList.size()-i-1;j++){
                if(csvCensusList.get(j).getState().compareTo(csvCensusList.get(j+1).getState())>0){
                    CSVStateCensus tempObj=csvCensusList.get(j);
                    csvCensusList.set(j,csvCensusList.get(j+1));
                    csvCensusList.set(j+1,tempObj);
                }
            }
        }
        writeToJsonFile(csvCensusList);
   }

    public void SortStateByPopulation(List<CSVStateCensus> csvCensusList) {
        for(int i=0;i<csvCensusList.size()-1;i++){
            for(int j=0;j<csvCensusList.size()-i-1;j++){
                if(csvCensusList.get(j).getPopulation()<(csvCensusList.get(j+1).getPopulation())){
                    CSVStateCensus tempObj=csvCensusList.get(j);
                    csvCensusList.set(j,csvCensusList.get(j+1));
                    csvCensusList.set(j+1,tempObj);
                }
            }
        }
        writeToJsonFile(csvCensusList);
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
